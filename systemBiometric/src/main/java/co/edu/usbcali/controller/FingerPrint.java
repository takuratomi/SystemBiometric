package co.edu.usbcali.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch._impl.DPFPSampleFactoryImpl;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;

import co.edu.usbcali.dto.HuellaDTO;
import co.edu.usbcali.logica.IBitacoraLogica;
import co.edu.usbcali.logica.IConsecutivoFactoryLogica;
import co.edu.usbcali.logica.IHuellaDerechaLogica;
import co.edu.usbcali.logica.IHuellaIzquierdaLogica;
import co.edu.usbcali.logica.IIngresoLogica;
import co.edu.usbcali.logica.IInoutDailyLogica;
import co.edu.usbcali.logica.IUsuarioLogica;
import co.edu.usbcali.modelo.Bitacora;
import co.edu.usbcali.modelo.HuellaDerecha;
import co.edu.usbcali.modelo.HuellaIzquierda;
import co.edu.usbcali.modelo.Ingreso;
import co.edu.usbcali.modelo.InoutDaily;
import co.edu.usbcali.modelo.Salida;
import co.edu.usbcali.modelo.Usuario;

@RestController
@RequestMapping("/fingerPrint")
public class FingerPrint {

	public static Logger log = LoggerFactory.getLogger(FingerPrint.class);

	@Autowired
	private IHuellaDerechaLogica huellaDerechaLogica;

	@Autowired
	private IHuellaIzquierdaLogica huellaIzquierdaLogica;

	@Autowired
	private IUsuarioLogica usuarioLogica;

	@Autowired
	private IBitacoraLogica bitacoraLogica;

	@Autowired
	private IInoutDailyLogica inoutDailyLogica;

	@Autowired
	private IConsecutivoFactoryLogica consecutivoFactoryLogica;

	@Autowired
	private IIngresoLogica ingresoLogica;

	@RequestMapping(value = "/dailyRegistrationValidation", method = RequestMethod.POST)
	public HuellaDTO dailyRegistrationValidation(@RequestBody HuellaDTO huellaDTO) {
		/**
		 * 0: inicio 10: encontrada 20: no encontroda
		 */
		int exitoHuella = 0;
		String respuestaFalla = "Huella no registrada.";
		String activo = "A";
		String patterDate = "dd-mm-yyyy : hh:mm:ss";
		
		String respuestaExito = "Usuario identificado.";
		String msjIngreso = "Ingreso ";
		String msjSalida = "Salida ";
		String msjInactivo = "El usuario no se encuentra activo.";
		String msjInactivoIdentificacion = "EL usuario no esta habilitado para ingresar con identificación.";
		String msjNoEiste = "El usuario con cedula & no existe";
		SimpleDateFormat sdf = new SimpleDateFormat(patterDate);
		Usuario usuario;
		Bitacora bitacora;
		List<InoutDaily> losInoutDaily;
		List<Usuario> lstUsuario;
		log.info(" ---------------------------------------------- ");
		log.info("entro en el servicio rest validacion por huella");

		try {
			
			List<HuellaDerecha> listHueDere = huellaDerechaLogica.consultarTodosHuellaDerecha();
			List<HuellaIzquierda> listHueIz = huellaIzquierdaLogica.consultarTodosHuellaIzquierda();

			for (HuellaIzquierda huellaIzquierda : listHueIz) {
				
				byte[] bitsHuellaizquierda = huellaIzquierda.getHuellaizquierda();

				// huella almacenada
				DPFPTemplate t = DPFPGlobal.getTemplateFactory().createTemplate();
				t.deserialize(bitsHuellaizquierda);
				DPFPSampleFactoryImpl dpfpSampleFactoryImpl = new DPFPSampleFactoryImpl();

				DPFPSample sample = dpfpSampleFactoryImpl.createSample(huellaDTO.getHuella());

				DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
				DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();

				DPFPFeatureSet features = extractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

				// Check quality of the sample and start verification if it's
				// good
				if (features != null) {
					// Compare the feature set with our template
					DPFPVerificationResult result = verificator.verify(features, t);
					log.info("resultado " + result.getFalseAcceptRate());
					if (result.isVerified())
					{
						// inicio del proceso de almacenamiento del registro ingreso o salida
						usuario = usuarioLogica.consultarUsuarioPorIdHuellaIzquierda(huellaIzquierda.getHullId2()).get(0);
						
						bitacora = bitacoraLogica.consultarPorIdBitacora(usuario.getBitacora().getBitaId());

						if (bitacora == null)
							throw new Exception("Ocurrio un problema intentando acceder a la bitacora");

						losInoutDaily = inoutDailyLogica.consultarPorFechaInoutDaily(huellaDTO.getFecha());

						if (losInoutDaily == null || losInoutDaily.size() == 0) {

							log.info("no encontro");
							InoutDaily inoutDaily = new InoutDaily();
							Ingreso ingreso = new Ingreso();

							Long consecutivoInoutDaiy = consecutivoFactoryLogica.getConsecutivoInDa();
							Long consecutivoIningreso = consecutivoFactoryLogica.getConsecutivoIngreso();
							String usuCreador = "ADMIN";
							Date fechaCreacion = new Date();

							ingreso.setIngrId(consecutivoIningreso);
							ingreso.setFechaingreso(huellaDTO.getFecha());
							ingreso.setHoraingreso(huellaDTO.getFecha());
							ingreso.setUsuCreador(usuCreador);
							ingreso.setFechaCreador(fechaCreacion);

							inoutDaily.setBitacora(bitacora);
							inoutDaily.setIndaId(consecutivoInoutDaiy);
							inoutDaily.setIngreso(ingreso);
							inoutDaily.setUsuCreador(usuCreador);
							inoutDaily.setFechaCreador(fechaCreacion);

							inoutDailyLogica.crearInoutDailyIngreso(inoutDaily, ingreso);
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy : hh:mm:ss");
							huellaDTO.setDesRetorno(msjIngreso + simpleDateFormat.format(huellaDTO.getFecha()));
							huellaDTO.setCodRetorno(10);							

						} else {
							log.info("encontro");
							String usuCreador = "ADMIN";
							Date fechaCreacion = new Date();
							/**
							 * validarCierre: esta variable permite conocer si se debe
							 * crear un nuevo ingreso. 0: crear nuevo ingreso 1: cerro
							 * ingreso
							 */
							int validarCierre = 0;
							// se realiza una busqueda de un ingreso sin salida
							for (InoutDaily inoutDaily : losInoutDaily) {
								if (inoutDaily.getSalida() == null
										&& inoutDaily.getBitacora().getBitaId() == bitacora.getBitaId()) {
									// se crea la salida
									Salida salida = new Salida();
									salida.setSaliId(consecutivoFactoryLogica.getConsecutivoSalida());
									salida.setFechasalida(huellaDTO.getFecha());
									salida.setHorasalida(huellaDTO.getFecha());
									salida.setUsuCreador(usuCreador);
									salida.setFechaCreador(fechaCreacion);

									inoutDaily.setSalida(salida);
									inoutDaily.setUsuModificador(usuCreador);
									inoutDaily.setFechaModificador(fechaCreacion);
									inoutDailyLogica.crearInoutDailySalida(inoutDaily, salida);
									SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy : hh:mm:ss");
									huellaDTO.setDesRetorno(msjSalida + simpleDateFormat.format(huellaDTO.getFecha()));
									huellaDTO.setCodRetorno(10);
									validarCierre++;
									break;
								}
							}

							// se crea nuevo ingreso
							if (validarCierre == 0) {

								log.info("no encontro");
								InoutDaily inoutDaily = new InoutDaily();
								Ingreso ingreso = new Ingreso();

								Long consecutivoInoutDaiy = consecutivoFactoryLogica.getConsecutivoInDa();
								Long consecutivoIningreso = consecutivoFactoryLogica.getConsecutivoIngreso();
								usuCreador = "ADMIN";
								fechaCreacion = new Date();

								ingreso.setIngrId(consecutivoIningreso);
								ingreso.setFechaingreso(huellaDTO.getFecha());
								ingreso.setHoraingreso(huellaDTO.getFecha());
								ingreso.setUsuCreador(usuCreador);
								ingreso.setFechaCreador(fechaCreacion);

								inoutDaily.setBitacora(bitacora);
								inoutDaily.setIndaId(consecutivoInoutDaiy);
								inoutDaily.setIngreso(ingreso);
								inoutDaily.setUsuCreador(usuCreador);
								inoutDaily.setFechaCreador(fechaCreacion);

								inoutDailyLogica.crearInoutDailyIngreso(inoutDaily, ingreso);
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy : hh:mm:ss");
								huellaDTO.setDesRetorno(msjIngreso + simpleDateFormat.format(huellaDTO.getFecha()));
								huellaDTO.setCodRetorno(10);								
							}
						}
						exitoHuella++;
						lstUsuario=null;								
						log.info("Huella coincide");
						break;
					}	
//					else
//					{
//						log.info("Huella no coincide.");
//					}
						
					log.info("Sigue en el ciclo");
				}
				
			}
			lstUsuario =null;
			if (exitoHuella == 0) {
				for (HuellaDerecha huellaDerecha : listHueDere) {

					byte[] bitsHuellaDerecha = huellaDerecha.getHuelladerecha();

					// huella almacenada
					DPFPTemplate t = DPFPGlobal.getTemplateFactory().createTemplate();
					t.deserialize(bitsHuellaDerecha);
					DPFPSampleFactoryImpl dpfpSampleFactoryImpl = new DPFPSampleFactoryImpl();

					DPFPSample sample = dpfpSampleFactoryImpl.createSample(huellaDTO.getHuella());

					DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
					DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();

					DPFPFeatureSet features = extractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

					// Check quality of the sample and start verification if it's
					// good
					if (features != null) {
						// Compare the feature set with our template
						DPFPVerificationResult result = verificator.verify(features, t);
						log.info("resultado " + result.getFalseAcceptRate());
						if (result.isVerified())
						{
							// inicio del proceso de almacenamiento del registro ingreso o salida
							usuario = usuarioLogica.consultarUsuarioPorIdHuellaDerecha(huellaDerecha.getHudeId()).get(0);
							
							bitacora = bitacoraLogica.consultarPorIdBitacora(usuario.getBitacora().getBitaId());

							if (bitacora == null)
								throw new Exception("Ocurrio un problema intentando acceder a la bitacora");

							losInoutDaily = inoutDailyLogica.consultarPorFechaInoutDaily(huellaDTO.getFecha());

							if (losInoutDaily == null || losInoutDaily.size() == 0) {

								log.info("no encontro");
								InoutDaily inoutDaily = new InoutDaily();
								Ingreso ingreso = new Ingreso();

								Long consecutivoInoutDaiy = consecutivoFactoryLogica.getConsecutivoInDa();
								Long consecutivoIningreso = consecutivoFactoryLogica.getConsecutivoIngreso();
								String usuCreador = "ADMIN";
								Date fechaCreacion = new Date();

								ingreso.setIngrId(consecutivoIningreso);
								ingreso.setFechaingreso(huellaDTO.getFecha());
								ingreso.setHoraingreso(huellaDTO.getFecha());
								ingreso.setUsuCreador(usuCreador);
								ingreso.setFechaCreador(fechaCreacion);

								inoutDaily.setBitacora(bitacora);
								inoutDaily.setIndaId(consecutivoInoutDaiy);
								inoutDaily.setIngreso(ingreso);
								inoutDaily.setUsuCreador(usuCreador);
								inoutDaily.setFechaCreador(fechaCreacion);

								inoutDailyLogica.crearInoutDailyIngreso(inoutDaily, ingreso);
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy : hh:mm:ss");
								huellaDTO.setDesRetorno(msjIngreso + simpleDateFormat.format(huellaDTO.getFecha()));
								huellaDTO.setCodRetorno(10);							

							} else {
								log.info("encontro");
								String usuCreador = "ADMIN";
								Date fechaCreacion = new Date();
								/**
								 * validarCierre: esta variable permite conocer si se debe
								 * crear un nuevo ingreso. 0: crear nuevo ingreso 1: cerro
								 * ingreso
								 */
								int validarCierre = 0;
								// se realiza una busqueda de un ingreso sin salida
								for (InoutDaily inoutDaily : losInoutDaily) {
									if (inoutDaily.getSalida() == null
											&& inoutDaily.getBitacora().getBitaId() == bitacora.getBitaId()) {
										// se crea la salida
										Salida salida = new Salida();
										salida.setSaliId(consecutivoFactoryLogica.getConsecutivoSalida());
										salida.setFechasalida(huellaDTO.getFecha());
										salida.setHorasalida(huellaDTO.getFecha());
										salida.setUsuCreador(usuCreador);
										salida.setFechaCreador(fechaCreacion);

										inoutDaily.setSalida(salida);
										inoutDaily.setUsuModificador(usuCreador);
										inoutDaily.setFechaModificador(fechaCreacion);
										inoutDailyLogica.crearInoutDailySalida(inoutDaily, salida);
										SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy : hh:mm:ss");
										huellaDTO.setDesRetorno(msjSalida + simpleDateFormat.format(huellaDTO.getFecha()));
										huellaDTO.setCodRetorno(10);
										validarCierre++;
										break;
									}
								}

								// se crea nuevo ingreso
								if (validarCierre == 0) {

									log.info("no encontro");
									InoutDaily inoutDaily = new InoutDaily();
									Ingreso ingreso = new Ingreso();

									Long consecutivoInoutDaiy = consecutivoFactoryLogica.getConsecutivoInDa();
									Long consecutivoIningreso = consecutivoFactoryLogica.getConsecutivoIngreso();
									usuCreador = "ADMIN";
									fechaCreacion = new Date();

									ingreso.setIngrId(consecutivoIningreso);
									ingreso.setFechaingreso(huellaDTO.getFecha());
									ingreso.setHoraingreso(huellaDTO.getFecha());
									ingreso.setUsuCreador(usuCreador);
									ingreso.setFechaCreador(fechaCreacion);

									inoutDaily.setBitacora(bitacora);
									inoutDaily.setIndaId(consecutivoInoutDaiy);
									inoutDaily.setIngreso(ingreso);
									inoutDaily.setUsuCreador(usuCreador);
									inoutDaily.setFechaCreador(fechaCreacion);

									inoutDailyLogica.crearInoutDailyIngreso(inoutDaily, ingreso);
									SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy : hh:mm:ss");
									huellaDTO.setDesRetorno(msjIngreso + simpleDateFormat.format(huellaDTO.getFecha()));
									huellaDTO.setCodRetorno(10);								
								}
							}
							exitoHuella++;
							lstUsuario=null;
							log.info("Huella coincide");
							break;
						}	
//						else
//						{
//							log.info("Huella no coincide.");
//						}
							
						log.info("Sigue en el ciclo");
					}
				}
			}

			if (exitoHuella < 1) {
				huellaDTO.setDesRetorno(respuestaFalla);
			}

		} catch (Exception e) {
			// alguna otra exception 
			huellaDTO.setDesRetorno(e.getMessage());

		}

		return huellaDTO;
	}

	@RequestMapping(value = "/dailyRegistrationValidationIdentification", method = RequestMethod.POST)
	public HuellaDTO dailyRegistrationValidationIdentification(@RequestBody HuellaDTO huellaDTO) {
		/**
		 * 0: inicio 10: encontrada 30: validando
		 */
		log.info("entro en el servicio rest validacion por identificación");
		// int exitoHuella = 0;
		String activo = "A";
		String patterDate = "dd-mm-yyyy : hh:mm:ss";
		// String respuestaFalla = "Registro no valido con identificación. ";
		String respuestaFalla = "Usuario no encontrado. ";
		String respuestaExito = "Usuario identificado.";
		String msjIngreso = "Ingreso ";
		String msjSalida = "Salida ";
		String msjInactivo = "El usuario no se encuentra activo.";
		String msjInactivoIdentificacion = "EL usuario no esta habilitado para ingresar con identificación.";
		String msjNoEiste = "El usuario con cedula & no existe";
		SimpleDateFormat sdf = new SimpleDateFormat(patterDate);
		Usuario usuario;
		Bitacora bitacora;
		// InoutDaily inoutDaily;
		List<InoutDaily> losInoutDaily;
		try {

			usuario = usuarioLogica.consultarPorIdUsuario(huellaDTO.getCedula());

			if (usuario == null) {
				huellaDTO.setCodRetorno(20);
				huellaDTO.setDesRetorno(msjNoEiste);
			} else {

				if (!usuario.getEstado().trim().equals(activo))
					throw new Exception(msjInactivo);

				if (!usuario.getIngresoidentificacion().trim().equals(activo))
					throw new Exception(msjInactivoIdentificacion);
				// inicio del proceso de almacenamiento del registro ingreso o salida
				bitacora = bitacoraLogica.consultarPorIdBitacora(usuario.getBitacora().getBitaId());

				if (bitacora == null)
					throw new Exception("Ocurrio un problema intentando acceder a la bitacora");

				losInoutDaily = inoutDailyLogica.consultarPorFechaInoutDaily(huellaDTO.getFecha());

				if (losInoutDaily == null || losInoutDaily.size() == 0) {

					log.info("no encontro");
					InoutDaily inoutDaily = new InoutDaily();
					Ingreso ingreso = new Ingreso();

					Long consecutivoInoutDaiy = consecutivoFactoryLogica.getConsecutivoInDa();
					Long consecutivoIningreso = consecutivoFactoryLogica.getConsecutivoIngreso();
					String usuCreador = "ADMIN";
					Date fechaCreacion = new Date();

					ingreso.setIngrId(consecutivoIningreso);
					ingreso.setFechaingreso(huellaDTO.getFecha());
					ingreso.setHoraingreso(huellaDTO.getFecha());
					ingreso.setUsuCreador(usuCreador);
					ingreso.setFechaCreador(fechaCreacion);

					inoutDaily.setBitacora(bitacora);
					inoutDaily.setIndaId(consecutivoInoutDaiy);
					inoutDaily.setIngreso(ingreso);
					inoutDaily.setUsuCreador(usuCreador);
					inoutDaily.setFechaCreador(fechaCreacion);

					inoutDailyLogica.crearInoutDailyIngreso(inoutDaily, ingreso);
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy : hh:mm:ss");
					huellaDTO.setDesRetorno(msjIngreso + simpleDateFormat.format(huellaDTO.getFecha()));
					huellaDTO.setCodRetorno(10);

				} else {
					log.info("encontro");
					String usuCreador = "ADMIN";
					Date fechaCreacion = new Date();
					/**
					 * validarCierre: esta variable permite conocer si se debe
					 * crear un nuevo ingreso. 0: crear nuevo ingreso 1: cerro
					 * ingreso
					 */
					int validarCierre = 0;
					// se realiza una busqueda de un ingreso sin salida
					for (InoutDaily inoutDaily : losInoutDaily) {
						if (inoutDaily.getSalida() == null
								&& inoutDaily.getBitacora().getBitaId() == bitacora.getBitaId()) {
							// se crea la salida
							Salida salida = new Salida();
							salida.setSaliId(consecutivoFactoryLogica.getConsecutivoSalida());
							salida.setFechasalida(huellaDTO.getFecha());
							salida.setHorasalida(huellaDTO.getFecha());
							salida.setUsuCreador(usuCreador);
							salida.setFechaCreador(fechaCreacion);

							inoutDaily.setSalida(salida);
							inoutDaily.setUsuModificador(usuCreador);
							inoutDaily.setFechaModificador(fechaCreacion);
							inoutDailyLogica.crearInoutDailySalida(inoutDaily, salida);
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy : hh:mm:ss");
							huellaDTO.setDesRetorno(msjSalida + simpleDateFormat.format(huellaDTO.getFecha()));
							huellaDTO.setCodRetorno(10);
							validarCierre++;
							break;
						}
					}

					// se crea nuevo ingreso
					if (validarCierre == 0) {

						log.info("no encontro");
						InoutDaily inoutDaily = new InoutDaily();
						Ingreso ingreso = new Ingreso();

						Long consecutivoInoutDaiy = consecutivoFactoryLogica.getConsecutivoInDa();
						Long consecutivoIningreso = consecutivoFactoryLogica.getConsecutivoIngreso();
						usuCreador = "ADMIN";
						fechaCreacion = new Date();

						ingreso.setIngrId(consecutivoIningreso);
						ingreso.setFechaingreso(huellaDTO.getFecha());
						ingreso.setHoraingreso(huellaDTO.getFecha());
						ingreso.setUsuCreador(usuCreador);
						ingreso.setFechaCreador(fechaCreacion);

						inoutDaily.setBitacora(bitacora);
						inoutDaily.setIndaId(consecutivoInoutDaiy);
						inoutDaily.setIngreso(ingreso);
						inoutDaily.setUsuCreador(usuCreador);
						inoutDaily.setFechaCreador(fechaCreacion);

						inoutDailyLogica.crearInoutDailyIngreso(inoutDaily, ingreso);
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy : hh:mm:ss");
						huellaDTO.setDesRetorno(msjIngreso + simpleDateFormat.format(huellaDTO.getFecha()));
						huellaDTO.setCodRetorno(10);
					}

				}
			}
		} catch (Exception e) {
			huellaDTO.setCodRetorno(55);
			huellaDTO.setDesRetorno(e.getMessage());
			log.error(e.getMessage());
		}
		return huellaDTO;

	}

//	@RequestMapping(value = "/dailyRegistrationValidation1", method = RequestMethod.POST)
//	public void dailyRegistrationValidation1(@RequestBody HuellaDTO huellaDTO) {
//		/**
//		 * 0: inicio 10: encontrada 20: no encontroda
//		 */
//		int exitoHuella = 0;
//		String respuestaFalla = "Huella no registrada.";
//		String respuestaCorrecta = "Encontrado";
//
//		try {
//			List<HuellaDerecha> listHueDere = huellaDerechaLogica.consultarTodosHuellaDerecha();
//			List<HuellaIzquierda> listHueIz = huellaIzquierdaLogica.consultarTodosHuellaIzquierda();
//
//			for (HuellaIzquierda huellaIzquierda : listHueIz) {
//				
//				byte[] bitsHuellaizquierta = huellaIzquierda.getHuellaizquierda();
//
//				// huella almacenada
//				DPFPTemplate t = DPFPGlobal.getTemplateFactory().createTemplate();
//				t.deserialize(bitsHuellaizquierta);
//				DPFPSampleFactoryImpl dpfpSampleFactoryImpl = new DPFPSampleFactoryImpl();
//
//				DPFPSample sample = dpfpSampleFactoryImpl.createSample(huellaDTO.getHuella());
//
//				DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
//				DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();
//
//				DPFPFeatureSet features = extractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
//
//				// Check quality of the sample and start verification if it's
//				// good
//				if (features != null) {
//					// Compare the feature set with our template
//					DPFPVerificationResult result = verificator.verify(features, t);
//					log.info("resultado " + result.getFalseAcceptRate());
//					if (result.isVerified())
//						
//						log.info("The fingerprint was VERIFIED.");
//					else
//						log.info("The fingerprint was NOT VERIFIED.");
//				}
//
//			}
//			if(exitoHuella != 10)
//			{
//				exitoHuella = 0;
//				for (HuellaDerecha huellaDerecha : listHueDere) {
//					
//					byte[] bitsHuellaDerecha= huellaDerecha.getHuelladerecha();
//
//					// huella almacenada
//					DPFPTemplate t = DPFPGlobal.getTemplateFactory().createTemplate();
//					t.deserialize(bitsHuellaDerecha);
//					DPFPSampleFactoryImpl dpfpSampleFactoryImpl = new DPFPSampleFactoryImpl();
//
//					DPFPSample sample = dpfpSampleFactoryImpl.createSample(huellaDTO.getHuella());
//
//					DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
//					DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();
//
//					DPFPFeatureSet features = extractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
//
//					// Check quality of the sample and start verification if it's
//					// good
//					if (features != null) {
//						// Compare the feature set with our template
//						DPFPVerificationResult result = verificator.verify(features, t);
//						log.info("resultado " + result.getFalseAcceptRate());
//						if (result.isVerified())
//							log.info("The fingerprint was VERIFIED.");
//						else
//							log.info("The fingerprint was NOT VERIFIED.");
//					}				
//			}
//			
//			}
//
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//		;
//
//	}

}
