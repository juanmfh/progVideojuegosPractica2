package simulador;

import java.awt.*;
import javax.swing.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.ArrayList;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.bulletphysics.dynamics.*;
import com.bulletphysics.collision.broadphase.AxisSweep3;
import com.bulletphysics.collision.dispatch.*;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.image.TextureLoader;
import figuras.FiguraBase;
import figuras.Moneda;
import figuras.Personaje;

public class Juego extends JFrame implements Runnable {

    int estadoJuego = 0;
    SimpleUniverse universo;
    BoundingSphere limites = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
    public String rutaCarpetaProyecto = System.getProperty("user.dir") + "/";
    Thread hebra = new Thread(this);
    ArrayList<simulador.Figura> listaObjetosFisicos = new ArrayList<Figura>();
    ArrayList<simulador.Figura> listaObjetosNoFisicos = new ArrayList<Figura>();
    public static DiscreteDynamicsWorld mundoFisico;
    BranchGroup conjunto = new BranchGroup();
    public boolean actualizandoFisicas, mostrandoFisicas;
    public float tiempoJuego;
    // Pesonajes importantes del juego
    Figura personaje;
    Shape3D textShape;
    BranchGroup escena;
    BranchGroup letras;
    JLabel monedas;
    boolean haEntradoEstrella = false;
    JPanel Controles;
    int contadorMensaje = 0;

    public Juego() {
        CollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
        CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);
        Vector3f worldAabbMin = new Vector3f(-10000, -10000, -10000);
        Vector3f worldAabbMax = new Vector3f(10000, 10000, 10000);
        AxisSweep3 broadphase = new AxisSweep3(worldAabbMin, worldAabbMax);
        SequentialImpulseConstraintSolver solver = new SequentialImpulseConstraintSolver();
        mundoFisico = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration);
        mundoFisico.setGravity(new Vector3f(0, -10, 0));

        Container GranPanel = getContentPane();
        //MIOINICIO-----
        Controles = new JPanel();

        //MIOFIN----

        Canvas3D zonaDibujo = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        zonaDibujo.setPreferredSize(new Dimension(1280, 720));
        GranPanel.add(zonaDibujo, BorderLayout.CENTER);

        //MIOINICIO----

        GranPanel.add(Controles, BorderLayout.NORTH);

        JLabel etiqueta = new JLabel("Monedas: ");
        monedas = new JLabel("0");
        JLabel iconoMonedas = new JLabel();
        iconoMonedas.setIcon(new javax.swing.ImageIcon(rutaCarpetaProyecto + "NewSuperMarioBros-Coin.png"));
        etiqueta.setFont(new Font("Arial", Font.BOLD, 24));
        monedas.setFont(new Font("Arial", Font.BOLD, 24));
        Color c = new Color(0, 153, 255, 255);
        Controles.setBackground(c);
        Controles.add(etiqueta);
        Controles.add(monedas);
        Controles.add(iconoMonedas);

        pack();
        //MIOFIN----
        universo = new SimpleUniverse(zonaDibujo);

        escena = crearEscena();
        escena.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        escena.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        escena.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        escena.setCapability(BranchGroup.ALLOW_DETACH);

        // Camara libre
        OrbitBehavior B = new OrbitBehavior(zonaDibujo);
        B.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0));
        universo.getViewingPlatform().setViewPlatformBehavior(B);

        escena.compile();
        universo.getViewingPlatform().setNominalViewingTransform();
        universo.addBranchGraph(escena);

        hebra.start();
    }

    BranchGroup crearEscena() {
        BranchGroup objRoot = new BranchGroup();
        conjunto = new BranchGroup();
        objRoot.addChild(conjunto);
        conjunto.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        conjunto.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        ComportamientoMostrar mostrar = new ComportamientoMostrar(this);
        DirectionalLight LuzDireccional = new DirectionalLight(new Color3f(10f, 10f, 10f), new Vector3f(1f, 0f, -1f));
        BoundingSphere limitesLuz = new BoundingSphere(new Point3d(-15, 10, 15), 100.0); //Localizacion de fuente/paso de luz
        objRoot.addChild(LuzDireccional);
        mostrar.setSchedulingBounds(limites);
        LuzDireccional.setInfluencingBounds(limitesLuz);
        TextureLoader bgTexture = new TextureLoader(rutaCarpetaProyecto + "fondoFull.jpg", this);
        Background bg = new Background(bgTexture.getImage());
        BoundingSphere limitesFondo = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        bg.setApplicationBounds(limitesFondo);
        BranchGroup backGeoBranch = new BranchGroup();
        bg.setGeometry(backGeoBranch);
        objRoot.addChild(bg);
        objRoot.addChild(mostrar);

        // crear mundo
        objRoot.addChild(Mundo.crearMundo());


        //Letras
        Font3D font3d = new Font3D(new Font("Helvetica", Font.PLAIN, 1), 1, new FontExtrusion());
        Text3D textGeom = new Text3D(font3d, "META", new Point3f(11f, 2.5f, -4.5f));
        textShape = new Shape3D(textGeom);
        textShape.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        textShape.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        textShape.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        textShape.setCapability(BranchGroup.ALLOW_DETACH);
        objRoot.addChild(textShape);

        return objRoot;
    }

    void cargarContenido() {
        //Creando el personaje del juego, controlado por teclado. Tambien se pudo haber creado en CrearEscena()
        float masa = 1f;
        float radio = 1f;
        float posX = 0f;
        float posY = -1.5f, posZ = 0f;
        float elasticidad = 0.5f;
        float dumpingLineal = 0.5f;

        personaje = new Personaje(0.5f, conjunto, listaObjetosFisicos, this);
        personaje.crearPropiedades(masa, elasticidad, dumpingLineal, posX, posY, posZ, mundoFisico);
        personaje.cuerpoRigido.setDamping(0.7f, 0.9f);

        //Figura Base 1
        FiguraBase figuraBase = new FiguraBase(new Vector3f(1f, 0.2f, 1f), conjunto, listaObjetosFisicos, this);
        figuraBase.crearPropiedades(8f, elasticidad, dumpingLineal, 12, 1, 4, mundoFisico);
        figuraBase.cuerpoRigido.setDamping(0.7f, 0.9f);

        //Figura Base 2
        FiguraBase figuraBase2 = new FiguraBase(new Vector3f(1f, 0.2f, 1f), conjunto, listaObjetosFisicos, this);
        figuraBase2.crearPropiedades(8f, elasticidad, dumpingLineal, 12, 1, -0.5f, mundoFisico);
        figuraBase2.cuerpoRigido.setDamping(0.7f, 0.9f);

        
        float friccion = 0.5f;
        utilidades.TerrenoSimple terreno = new utilidades.TerrenoSimple(40, 40, -5, -3f, -12, "unaTextura_Desabilitada", conjunto, mundoFisico, friccion);
    }

    void actualizar(float dt) {


        //ACTUALIZAR EL ESTADO DEL JUEGO
        if (personaje.posiciones[0] - 12.34f < 0.25f && personaje.posiciones[0] - 12.34f > -0.25f
                && personaje.posiciones[1] - 2f < 0.25f && personaje.posiciones[1] - 2f > -0.25f
                && personaje.posiciones[2] + 3.57f < 0.25f && personaje.posiciones[2] + 3.57f > -0.25f
                && haEntradoEstrella) {

            mostrarFinal();
            
        }

        if (estadoJuego != -1) {
            if (this.getMonedas() == 1 && !haEntradoEstrella) {
                Mundo.raizEstrella.addChild(Mundo.estrella);
                haEntradoEstrella = true;
            }

            for (Moneda m : Mundo.listaMonedas) {
                float difX = Math.abs(personaje.posiciones[0] - m.getPosicion().x);
                float difY = Math.abs(personaje.posiciones[1] - m.getPosicion().y);
                float difZ = Math.abs(personaje.posiciones[2] - m.getPosicion().z);

                if (difX < 0.25f && difY < 0.25f && difZ < 0.25f) {
                    Mundo.bgRaizMonedas.removeChild(m.getBgmoneda());
                    if (!m.isCogida()) {
                        consigueMoneda();
                        m.setCogida(true);
                    }
                }

            }

            if (estadoJuego == 0) {
                //perseguidor.asignarObjetivo(personaje, 15f);
                if (tiempoJuego > 1000) {
                    estadoJuego = 1;
                }
            } else if (estadoJuego == 1) {
                //Removiendo las figuras dinamicas. El juego continua 10 segundos mas
                int i = 1;
                while (listaObjetosFisicos.size() > i) {
                    listaObjetosFisicos.get(i).remover();      //Elimina a pertir de la i-esima figura
                }
                if (tiempoJuego > 20000) {
                    estadoJuego = -1;                                                                    //Con estado del juego -1 el juego termina
                    System.out.println("Fin del juego");
                }
            }

            //ACTUALIZAR DATOS DE FUERZAS DEL PERSONAJE CONTROLADO POR EL JUGADOR
            if (personaje != null) {
                float fuerzaElevacion = 0, fuerzaLateral = 0;
                //--MIO

                if (personaje.soltadoTeclaIzquierda) {
                    personaje.soltadoTeclaIzquierda = false;
                    fuerzaLateral = -personaje.masa * 10f * personaje.contadorGiro; //aplicamos fuerza opuesta
                    personaje.contadorGiro = 0;

                }

                if (personaje.soltadoTeclaDerecha) {
                    personaje.soltadoTeclaDerecha = false;
                    fuerzaLateral = personaje.masa * 10f * personaje.contadorGiro; //aplicamos fuerza opuesta
                    personaje.contadorGiro = 0;

                }
                //MIO---  
                if (personaje.adelante) {

                    fuerzaElevacion = personaje.masa * 10f * 2.5f;
                }
                if (personaje.atras) {

                    fuerzaElevacion = -personaje.masa * 10f * 2.5f;
                }

                if (personaje.derecha) {
                    //MIO
                    personaje.contadorGiro++;
                    //MIO
                    fuerzaLateral = -personaje.masa * 20f;
                }
                if (personaje.izquierda) {
                    //MIO
                    personaje.contadorGiro++;
                    //MIO
                    fuerzaLateral = personaje.masa * 20f;
                }

                Vector3d direccionFrente = personaje.conseguirDireccionFrontal();
                personaje.cuerpoRigido.applyCentralForce(new Vector3f((float) direccionFrente.x * fuerzaElevacion * 0.1f, 0, (float) direccionFrente.z * fuerzaElevacion * 0.1f));
                personaje.cuerpoRigido.applyTorque(new Vector3f(0, fuerzaLateral, 0));
            }

            //ACTUALIZAR DATOS DE FUERZAS DE LAS FIGURAS AUTONOMAS  (ej. para que cada figura pueda persiguir su objetivo)
            for (int i = 0; i < this.listaObjetosFisicos.size(); i++) {
                listaObjetosFisicos.get(i).actualizar();
            }

            //ACTUALIZAR DATOS DE LOCALIZACION DE FIGURAS FISICAS
            this.actualizandoFisicas = true;
            try {
                mundoFisico.stepSimulation(dt);    //mundoFisico.stepSimulation ( dt  ,50000, dt*0.2f);
            } catch (Exception e) {
                System.out.println("JBullet forzado. No debe crearPropiedades de solidoRigidos durante la actualizacion stepSimulation");
            }
            this.actualizandoFisicas = false;
            tiempoJuego = tiempoJuego + dt;
        }

    }

    void mostrar() throws Exception {
        //MOSTRAR FIGURAS FISICAS (muestra el componente visual de la figura, con base en los datos de localizacion del componente fisico)
        this.mostrandoFisicas = true;
        try {
            if ((mundoFisico.getCollisionObjectArray().size() != 0) && (listaObjetosFisicos.size() != 0)) {
                for (int idFigura = 0; idFigura <= this.listaObjetosFisicos.size() - 1; idFigura++) {     // Actualizar posiciones fisicas y graficas de los objetos.
                    try {
                        int idFisico = listaObjetosFisicos.get(idFigura).identificadorFisico;
                        CollisionObject objeto = mundoFisico.getCollisionObjectArray().get(idFisico); //
                        RigidBody cuerpoRigido = RigidBody.upcast(objeto);
                        listaObjetosFisicos.get(idFigura).mostrar(cuerpoRigido);
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
        actualizarCamara();
        for (Moneda m : Mundo.listaMonedas) {
            m.mostrar();
        }
        this.mostrandoFisicas = false;

    }

    public void run() {
        cargarContenido();
        float dt = 3f / 100f;
        int tiempoDeEspera = (int) (dt * 1000);
        while (estadoJuego != -1) {
            try {
                actualizar(dt);

            } catch (Exception e) {
                System.out.println("Error durante actualizar. Estado del juego " + estadoJuego + e.getMessage());
            }
            try {
                Thread.sleep(tiempoDeEspera);
            } catch (Exception e) {
            }
        }
    }

    void colocarCamara(SimpleUniverse universo, Point3d posicionCam, Point3d objetivoCamara) {
        Point3d posicionCamara = new Point3d(posicionCam.x + 0.001, posicionCam.y + 0.001d, posicionCam.z + 0.001);
        Transform3D datosConfiguracionCamara = new Transform3D();
        datosConfiguracionCamara.lookAt(posicionCamara, objetivoCamara, new Vector3d(0.001, 1.001, 0.001));
        try {
            datosConfiguracionCamara.invert();
            TransformGroup TGcamara = universo.getViewingPlatform().getViewPlatformTransform();
            TGcamara.setTransform(datosConfiguracionCamara);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void actualizarCamara() {

        /*TransformGroup transformGroupCamara = universo.getViewingPlatform().getViewPlatformTransform();
         Transform3D transformCamara = new Transform3D();
         transformGroupCamara.getTransform(transformCamara);*/


        Point3d posicionCamara;
        Point3d objetivoCamara;
        if (personaje.posiciones[0] > 9) {
            posicionCamara = new Point3d(personaje.posiciones[0] + 4, personaje.posiciones[1] + 3, personaje.posiciones[2] + 4);
            objetivoCamara = new Point3d(personaje.posiciones[0] - 1, personaje.posiciones[1] - 1, personaje.posiciones[2] - 1);

        } else {
            posicionCamara = new Point3d(personaje.posiciones[0] - 4, personaje.posiciones[1] + 3, personaje.posiciones[2] - 4);
            objetivoCamara = new Point3d(personaje.posiciones[0] + 1, personaje.posiciones[1] + 1, personaje.posiciones[2] + 1);
        }
        Transform3D datosConfiguracionCamara = new Transform3D();
        datosConfiguracionCamara.lookAt(posicionCamara, objetivoCamara, new Vector3d(1, 1, 1)); // posSonar
        try {
            datosConfiguracionCamara.invert();
            TransformGroup TGcamara = universo.getViewingPlatform().getViewPlatformTransform();
            TGcamara.setTransform(datosConfiguracionCamara);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void setMonedas(int numMonedas) {
        monedas.setText(numMonedas + "");
    }

    public void mostrarFinal() {
        monedas.setText("---¡¡¡YOU WIN!!!---");
        
        if (contadorMensaje == 30) {
            Controles.setBackground(Color.red);
            System.out.println("entrarojo");
        }
        if (contadorMensaje == 60) {
            Controles.setBackground(Color.blue);
            contadorMensaje = 0;
            System.out.println("entraazul");
        }
        System.out.println(contadorMensaje);
        estadoJuego = -1;
        contadorMensaje++;

    }

    public void consigueMoneda() {
        monedas.setText((Integer.parseInt(monedas.getText()) + 1) + "");
    }

    public int getMonedas() {
        return Integer.parseInt(monedas.getText());
    }

    public static void main(String[] args) {
        Juego x = new Juego();
        x.setTitle("Pseudo-Mario");
        x.setSize(800, 640);
        x.setVisible(true);
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.colocarCamara(x.universo, new Point3d(2.5f, 4f, 4f), new Point3d(3, 0, 0));
    }
}
