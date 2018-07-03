
package co.edu.usbcali.logica;

import java.awt.Image;

import javax.swing.SwingUtilities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.digitalpersona.onetouch.DPFPCaptureFeedback;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;

import co.edu.usbcali.vista.UsuarioEmpleadoVista;

public class CaptureForm {
	
	private final String IMAGEN_WIDHT = "240";
	private final String IMAGEN_HEIGHT = "280";
	
	
	private DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
//	private JLabel picture = new JLabel();
//	private JTextField prompt = new JTextField();
	
	public UsuarioEmpleadoVista getUsuarioEmpleadoVista() {
		return usuarioEmpleadoVista;
	}

	public void setUsuarioEmpleadoVista(UsuarioEmpleadoVista usuarioEmpleadoVista) {
		this.usuarioEmpleadoVista = usuarioEmpleadoVista;
	}

//	private JTextArea log = new JTextArea();
//	private JTextField status = new JTextField("[status line]");
	private UsuarioEmpleadoVista usuarioEmpleadoVista;
	
	
    public CaptureForm(UsuarioEmpleadoVista usuarioEmpleadoVista) {    	
        super ();
        usuarioEmpleadoVista = usuarioEmpleadoVista;
        
//        usuarioEmpleadoVista.getImagen().setAlt("Imagen del indice derecho");
//        usuarioEmpleadoVista.getImagen().setHeight(IMAGEN_HEIGHT);
//        usuarioEmpleadoVista.getImagen().setWidth(IMAGEN_WIDHT);
        
        init();
        start();
    }
    public CaptureForm() {    	
//        super ();
//        usuarioEmpleadoVista = usuarioEmpleadoVista;
        
//        usuarioEmpleadoVista.getImagen().setAlt("Imagen del indice derecho");
//        usuarioEmpleadoVista.getImagen().setHeight(IMAGEN_HEIGHT);
//        usuarioEmpleadoVista.getImagen().setWidth(IMAGEN_WIDHT);
        
//        init();
//        start();
    }
        
//		picture.setPreferredSize(new Dimension(240, 280));
//		picture.setBorder(BorderFactory.createLoweredBevelBorder());
//		prompt.setFont(UIManager.getFont("Panel.font"));
//		prompt.setEditable(false);
//		prompt.setColumns(40);
//		prompt.setMaximumSize(prompt.getPreferredSize());
//		prompt.setBorder(
//				BorderFactory.createCompoundBorder(
//					BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Aviso:"),
//					BorderFactory.createLoweredBevelBorder()
//				));
//		log.setColumns(40);
//		log.setEditable(false);
//		log.setFont(UIManager.getFont("Panel.font"));
//		JScrollPane logpane = new JScrollPane(log);
//		logpane.setBorder(
//				BorderFactory.createCompoundBorder(
//					BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Status:"),
//					BorderFactory.createLoweredBevelBorder()
//				));
		
//		status.setEditable(false);
//		status.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//		status.setFont(UIManager.getFont("Panel.font"));
//		
//		JButton quit = new JButton("Cerrar");
//        quit.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) { setVisible(false); }});
//
//		JPanel right = new JPanel(new BorderLayout());
//		right.setBackground(Color.getColor("control"));
//		right.add(prompt, BorderLayout.PAGE_START);
//		right.add(logpane, BorderLayout.CENTER);

//		JPanel center = new JPanel(new BorderLayout());
//		center.setBackground(Color.getColor("control"));
//		center.add(right, BorderLayout.CENTER);
//		center.add(picture, BorderLayout.LINE_START);
//		center.add(status, BorderLayout.PAGE_END);
//			
//		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.TRAILING));
//		bottom.setBackground(Color.getColor("control"));
//		bottom.add(quit);
//
//		setLayout(new BorderLayout());
//		add(center, BorderLayout.CENTER);
//		add(bottom, BorderLayout.PAGE_END);
//
        
//		this.addComponentListener(new ComponentAdapter() {
//			@Override public void componentShown(ComponentEvent e) {
//				init();
//				start();
//			}
//			@Override public void componentHidden(ComponentEvent e) {
//				stop();
//			}
//			
//		});
//		
//		pack();
//        setLocationRelativeTo(null);
//	}

	public void init()
	{
		capturer.addDataListener(new DPFPDataAdapter() {
			@Override public void dataAcquired(final DPFPDataEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("La muestra de la huella dactilar fue capturada.");
					setPrompt("Escanee de nuevo la misma huella digital.");
					process(e.getSample());
				}});
			}
		});
		capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {
			@Override public void readerConnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
		 			makeReport("El lector de huellas dactilares se conectó.");
				}});
			}
			@Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("El lector de huellas dactilares fue desconectado.");
				}});
			}
		});
		capturer.addSensorListener(new DPFPSensorAdapter() {
			@Override public void fingerTouched(final DPFPSensorEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("El lector de huellas dactilares fue tocado.");
				}});
			}
			@Override public void fingerGone(final DPFPSensorEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("El dedo se retiró del lector de huella digital.");
				}});
			}
		});
		capturer.addImageQualityListener(new DPFPImageQualityAdapter() {
			@Override public void onImageQuality(final DPFPImageQualityEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD))
						makeReport("La calidad de la muestra de huella dactilar es buena.");
					else
						makeReport("La calidad de la muestra de la huella digital es pobre.");
				}});
			}
		});
	}

	protected void process(DPFPSample sample)
	{
		// Draw fingerprint sample image.
		drawPicture(convertSampleToBitmap(sample));
	}

	public void start()
	{
		capturer.startCapture();
		setPrompt("Uso del lector de huellas digitales, escanear su huella digital.");
	}

	protected void stop()
	{
		capturer.stopCapture();
	}

	public void setStatus(String string) {
//		usuarioEmpleadoVista.getLblIntentos().setValue(string);
//		status.setText(string);
	}
	public void setPrompt(String string) {
//		usuarioEmpleadoVista.getPrompIz().setValue(string);
//		prompt.setText(string);
	}
	public void makeReport(String string) {
//		usuarioEmpleadoVista.getTxtAreaIz().setValue(string + "\n");
//		log.append(string + "\n");
	}
	
	public void drawPicture(Image image) {
//		picture.setIcon(new ImageIcon(
//			image.getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	protected Image convertSampleToBitmap(DPFPSample sample) {
		return DPFPGlobal.getSampleConversionFactory().createImage(sample);
	}

	protected DPFPFeatureSet extractFeatures(DPFPSample sample, DPFPDataPurpose purpose)
	{
		DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
		try {
			return extractor.createFeatureSet(sample, purpose);
		} catch (DPFPImageQualityException e) {
			return null;
		}
	}
	
	
}
