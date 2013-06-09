/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Container;
import java.util.ArrayList;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3d;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import simulador.DeteccionControlPersonaje;
import simulador.Figura;
import simulador.Juego;

/**
 *
 * @author JuanM
 */
public class Personaje extends Figura {

    public Scene escenaPersonaje1;
    public Vector3d direccion = new Vector3d(0, 0, 10);
    public float radio, alturaP, alturaDeOjos;
    boolean esPersonaje;
    TransformGroup moverPiernaD;
    TransformGroup moverPiernaI;
    TransformGroup moverBrazoD;
    TransformGroup moverBrazoI;
    TransformGroup moverAntebrazoD;
    TransformGroup moverAnterazoI;
    TransformGroup moverAntebrazoI;
    float angulo = 0;  // angulos de las extremidades
    float anguloSecundario = 0;
    float dir = 1;
    public boolean enSalto = false;
    Vector3f fuerzaSalto;

    public Personaje(float radio, BranchGroup conjunto, ArrayList<Figura> listaObjetosFisicos, Juego juego) {
        super(conjunto, listaObjetosFisicos, juego);
        esMDL = true; // para que no gire como una esfera
        esPersonaje = true;

        // Creacion de la apariencia
        Appearance apariencia = new Appearance();

        //Creacion de formas visuales y fisicas
        TransformGroup figuraVisual = crearPersonaje();
        SphereShape figuraFisica = new SphereShape(radio);
        ramaFisica = new CollisionObject();
        ramaFisica.setCollisionShape(figuraFisica);
        ramaVisible.addChild(desplazamientoFigura);
        desplazamientoFigura.addChild(figuraVisual);

        DeteccionControlPersonaje mueve = new DeteccionControlPersonaje(this);
        mueve.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0));
        ramaVisible.addChild(mueve);

    }

    public TransformGroup crearPersonaje() {
        BranchGroup personaje = crearBranchGroupPersonaje();



        alturaP = (float) 0.5f;
        alturaDeOjos = alturaP;
        Transform3D rotacionCombinada = new Transform3D();
        TransformGroup rotadorFigura = new TransformGroup(rotacionCombinada);
        rotadorFigura.addChild(personaje);
        return rotadorFigura;
    }

    public BranchGroup crearBranchGroupPersonaje() {
        BranchGroup objRoot = new BranchGroup();

        // Apariencia
         Appearance aparienciaCara = new Appearance();
        aparienciaCara.setTexture(new TextureLoader(System.getProperty("user.dir") + "//cara.jpg", new Container()).getTexture());
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        aparienciaCara.setTextureAttributes(texAttr);
        
        
        ColoringAttributes colorAzul = new ColoringAttributes(0f, 0f, 1f, ColoringAttributes.FASTEST);
        Appearance aparienciaAzul = new Appearance();
        aparienciaAzul.setColoringAttributes(colorAzul);

        ColoringAttributes colorVerde = new ColoringAttributes(0f, 1f, 0f, ColoringAttributes.FASTEST);
        Appearance aparienciaVerde = new Appearance();
        aparienciaVerde.setColoringAttributes(colorVerde);

        ColoringAttributes colorRojo = new ColoringAttributes(1f, 0f, 0f, ColoringAttributes.FASTEST);
        Appearance aparienciaRojo = new Appearance();
        aparienciaRojo.setColoringAttributes(colorRojo);

        ColoringAttributes colorBlanco = new ColoringAttributes(1f, 1f, 1f, ColoringAttributes.FASTEST);
        Appearance aparienciaBlanco = new Appearance();
        aparienciaBlanco.setColoringAttributes(colorBlanco);
        
        ColoringAttributes colorPiel = new ColoringAttributes(0.99f, 0.94f, 0.63f, ColoringAttributes.FASTEST);
        Appearance aparienciaPiel = new Appearance();
        aparienciaPiel.setColoringAttributes(colorPiel);

        //Pierna derecha
        //************************************************
        BranchGroup piernaDerecha = new BranchGroup();

        // Posiciona el pie derecho
        Transform3D posicionaPieD = new Transform3D();
        posicionaPieD.set(new Vector3f(0.0f, -0.15f, 0.02f));
        TransformGroup posicionaPieDTG = new TransformGroup(posicionaPieD);

        // Posiciona la pierna derecha
        Transform3D posicionaPiernaD = new Transform3D();
        posicionaPiernaD.set(new Vector3f(0.0f, -0.085f, 0.0f));
        TransformGroup posicionaPiernaDTG = new TransformGroup(posicionaPiernaD);

        // Desplaza el conjunto de la pierna derecha
        Transform3D extInfDDesplazado = new Transform3D();
        extInfDDesplazado.set(new Vector3f(-0.05f, 0.0f, 0.0f));
        TransformGroup extInfDDesplazadoTG = new TransformGroup(extInfDDesplazado);

        // Otorga los permisos a los TransformGroup para poder escribirlos.
        moverPiernaD = new TransformGroup();
        moverPiernaD.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        // Estructura
        extInfDDesplazadoTG.addChild(moverPiernaD);
        moverPiernaD.addChild(piernaDerecha);
        piernaDerecha.addChild(posicionaPieDTG);
        piernaDerecha.addChild(posicionaPiernaDTG);
        posicionaPieDTG.addChild(new Box(0.04f, 0.025f, 0.06f, aparienciaAzul));
        posicionaPiernaDTG.addChild(new Cylinder(0.04f, 0.17f, aparienciaRojo));

        //Pierna izquierda
        //************************************************
        BranchGroup piernaIzquierda = new BranchGroup();

        // Posiciona el pie derecho
        Transform3D posicionaPieI = new Transform3D();
        posicionaPieI.set(new Vector3f(0.0f, -0.15f, 0.02f));
        TransformGroup posicionaPieITG = new TransformGroup(posicionaPieI);

        // Posiciona la pierna derecha
        Transform3D posicionaPiernaI = new Transform3D();
        posicionaPiernaI.set(new Vector3f(0.0f, -0.085f, 0.0f));
        TransformGroup posicionaPiernaITG = new TransformGroup(posicionaPiernaI);

        // Desplaza el conjunto de la pierna derecha
        Transform3D extInfIDesplazado = new Transform3D();
        extInfIDesplazado.set(new Vector3f(0.05f, 0.0f, 0.0f));
        TransformGroup extInfIDesplazadoTG = new TransformGroup(extInfIDesplazado);

        // Otorga los permisos a los TransformGroup para poder escribirlos.
        moverPiernaI = new TransformGroup();
        moverPiernaI.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        // Estructura
        extInfIDesplazadoTG.addChild(moverPiernaI);
        moverPiernaI.addChild(piernaIzquierda);
        piernaIzquierda.addChild(posicionaPieITG);
        piernaIzquierda.addChild(posicionaPiernaITG);
        posicionaPieITG.addChild(new Box(0.04f, 0.025f, 0.06f, aparienciaAzul));
        posicionaPiernaITG.addChild(new Cylinder(0.04f, 0.17f, aparienciaRojo));

        // Tronco & Cabeza
        BranchGroup conjuntoTronco = new BranchGroup();

        // Desplazamiento cadera
        Transform3D caderaDesplazada = new Transform3D();
        caderaDesplazada.set(new Vector3f(0.0f, 0.045f, 0.0f));
        TransformGroup caderaDesplazadaTG = new TransformGroup(caderaDesplazada);
        caderaDesplazadaTG.addChild(new Sphere(0.09f, aparienciaRojo));

        // Desplazamiento tronco
        Transform3D troncoDesplazado = new Transform3D();
        troncoDesplazado.set(new Vector3f(0.0f, 0.08f, 0.0f));
        TransformGroup troncoDesplazadoTG = new TransformGroup(troncoDesplazado);
        troncoDesplazadoTG.addChild(new Cylinder(0.09f, 0.11f, aparienciaRojo));

        // Desplazamiento hombreras
        Transform3D hombrerasDesplazado = new Transform3D();
        hombrerasDesplazado.set(new Vector3f(0.0f, 0.10f, 0.0f));
        TransformGroup hombrerasDesplazadoTG = new TransformGroup(hombrerasDesplazado);
        hombrerasDesplazadoTG.addChild(new Sphere(0.09f, aparienciaRojo));

        // Desplaza todo el tronco( cadera, tronco y hombreras)
        Transform3D conjuntoTroncoDesplazado = new Transform3D();
        conjuntoTroncoDesplazado.set(new Vector3f(0.0f, -0.025f, 0.0f));
        TransformGroup conjuntoTroncoDesplazadoTG = new TransformGroup(conjuntoTroncoDesplazado);
        conjuntoTroncoDesplazadoTG.addChild(conjuntoTronco);

        // Cabeza
        Transform3D cabezaDesplazada = new Transform3D();
        cabezaDesplazada.set(new Vector3f(0.0f, 0.26f, 0.0f));
        TransformGroup cabezaDesplazadaTG = new TransformGroup(cabezaDesplazada);
        cabezaDesplazadaTG.addChild(new Sphere(0.1f, Sphere.GENERATE_TEXTURE_COORDS, aparienciaCara));
        
        // Nariz
        Transform3D narizDesplazada = new Transform3D();
        narizDesplazada.set(new Vector3f(0.0f, 0.24f, 0.08f));
        TransformGroup narizDesplazadaTG = new TransformGroup(narizDesplazada);
        narizDesplazadaTG.addChild(new Sphere(0.05f, aparienciaPiel));
        
        // Gorra
        Transform3D gorraDesplazada = new Transform3D();
        gorraDesplazada.set(new Vector3f(0.0f, 0.28f, 0.0f));
        TransformGroup gorraDesplazadaTG = new TransformGroup(gorraDesplazada);
        gorraDesplazadaTG.addChild(new Sphere(0.1f, aparienciaRojo));
        
        //Bisera
        Transform3D biseraDesplazada = new Transform3D();
        biseraDesplazada.set(new Vector3f(0.0f, 0.30f, 0.05f));
        TransformGroup biseraDesplazadaTG = new TransformGroup(biseraDesplazada);
        biseraDesplazadaTG.addChild(new Cylinder(0.1f,0.03f, aparienciaRojo));

        // Estructura
        conjuntoTronco.addChild(narizDesplazadaTG);
        conjuntoTronco.addChild(hombrerasDesplazadoTG);
        conjuntoTronco.addChild(troncoDesplazadoTG);
        conjuntoTronco.addChild(caderaDesplazadaTG);
        conjuntoTronco.addChild(cabezaDesplazadaTG);
        conjuntoTronco.addChild(gorraDesplazadaTG);
        conjuntoTronco.addChild(biseraDesplazadaTG);
        // Brazo derecho
        //**************************************************
        BranchGroup extSupDerecha = new BranchGroup();
        BranchGroup antebrazoDerecha = new BranchGroup();

        // Posiciona mano derecha
        Transform3D posicionaManoD = new Transform3D();
        posicionaManoD.set(new Vector3f(0.0f, -0.08f, 0.0f));
        TransformGroup posicionaManoDTG = new TransformGroup(posicionaManoD);

        // Posiciona el brazo derecho
        Transform3D posicionaBrazoD = new Transform3D();
        posicionaBrazoD.set(new Vector3f(0.0f, -0.05f, 0.0f));
        TransformGroup posicionaBrazoDTG = new TransformGroup(posicionaBrazoD);

        // Posiciona el codo
        Transform3D posicionaCodoD = new Transform3D();
        posicionaCodoD.set(new Vector3f(0.0f, 0.0f, 0.0f));
        TransformGroup posicionaCodoDTG = new TransformGroup(posicionaCodoD);

        // Posiciona el antebrazo
        Transform3D antebrazoDLevantado = new Transform3D();
        antebrazoDLevantado.set(new Vector3f(0.0f, -0.035f, 0.0f));
        TransformGroup antebrazoDLevantadoTG = new TransformGroup(antebrazoDLevantado);

        // Desplaza el antebrazo
        Transform3D antebrazoDDesplazado = new Transform3D();
        antebrazoDDesplazado.set(new Vector3f(0f, -0.06f, 0.0f));
        TransformGroup antebrazoDDesplazadoTG = new TransformGroup(antebrazoDDesplazado);

        // Desplaza la extremidad superior derecha
        Transform3D extSupDDesplazado = new Transform3D();
        extSupDDesplazado.set(new Vector3f(-0.1f, 0.1f, 0.0f));
        TransformGroup extSupDDesplazadoTG = new TransformGroup(extSupDDesplazado);

        // Otorga los permisos a los TransformGroup para poder escribirlos.
        moverBrazoD = new TransformGroup();
        moverAntebrazoD = new TransformGroup();
        moverBrazoD.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        moverAntebrazoD.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Estructura
        extSupDerecha.addChild(new Sphere(0.04f, aparienciaRojo));
        extSupDerecha.addChild(posicionaBrazoDTG);
        extSupDerecha.addChild(antebrazoDDesplazadoTG);
        antebrazoDDesplazadoTG.addChild(moverAntebrazoD);
        moverAntebrazoD.addChild(antebrazoDerecha);

        posicionaBrazoDTG.addChild(new Cylinder(0.03f, 0.04f, aparienciaRojo));

        antebrazoDerecha.addChild(antebrazoDLevantadoTG);
        antebrazoDerecha.addChild(posicionaManoDTG);
        antebrazoDerecha.addChild(posicionaCodoDTG);

        posicionaCodoDTG.addChild(new Sphere(0.03f, aparienciaRojo));
        posicionaManoDTG.addChild(new Sphere(0.05f, aparienciaBlanco));
        antebrazoDLevantadoTG.addChild(new Cylinder(0.03f, 0.045f, aparienciaRojo));
        moverBrazoD.addChild(extSupDerecha);
        extSupDDesplazadoTG.addChild(moverBrazoD);

        // Rotacion inicial brazo derecho
        Transform3D rotacionInicialbrazod = new Transform3D();
        rotacionInicialbrazod.rotZ(-Math.PI / 8);
        moverBrazoD.setTransform(rotacionInicialbrazod);

        // Rotacion inicial antebrazo derecho
        Transform3D rotacionInicialantebrazod = new Transform3D();
        rotacionInicialantebrazod.rotZ(Math.PI / 12);
        moverAntebrazoD.setTransform(rotacionInicialantebrazod);

        // Brazo izquierdo
        //**************************************************
        BranchGroup extSupIzquierda = new BranchGroup();
        BranchGroup antebrazoIzquierda = new BranchGroup();

        // Posiciona mano derecha
        Transform3D posicionaManoI = new Transform3D();
        posicionaManoI.set(new Vector3f(0.0f, -0.08f, 0.0f));
        TransformGroup posicionaManoITG = new TransformGroup(posicionaManoI);

        // Posiciona el brazo derecho
        Transform3D posicionaBrazoI = new Transform3D();
        posicionaBrazoI.set(new Vector3f(0.0f, -0.05f, 0.0f));
        TransformGroup posicionaBrazoITG = new TransformGroup(posicionaBrazoI);

        // Posiciona el codo
        Transform3D posicionaCodoI = new Transform3D();
        posicionaCodoI.set(new Vector3f(0.0f, 0.0f, 0.0f));
        TransformGroup posicionaCodoITG = new TransformGroup(posicionaCodoI);

        // Posiciona el antebrazo
        Transform3D antebrazoILevantado = new Transform3D();
        antebrazoILevantado.set(new Vector3f(0.0f, -0.035f, 0.0f));
        TransformGroup antebrazoILevantadoTG = new TransformGroup(antebrazoILevantado);

        // Desplaza el antebrazo
        Transform3D antebrazoIDesplazado = new Transform3D();
        antebrazoIDesplazado.set(new Vector3f(0f, -0.06f, 0.0f));
        TransformGroup antebrazoIDesplazadoTG = new TransformGroup(antebrazoIDesplazado);

        // Desplaza la extremidad superior derecha
        Transform3D extSupIDesplazado = new Transform3D();
        extSupIDesplazado.set(new Vector3f(0.1f, 0.1f, 0.0f));
        TransformGroup extSupIDesplazadoTG = new TransformGroup(extSupIDesplazado);

        // Otorga los permisos a los TransformGroup para poder escribirlos.
        moverBrazoI = new TransformGroup();
        moverAntebrazoI = new TransformGroup();
        moverBrazoI.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        moverAntebrazoI.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Estructura
        extSupIzquierda.addChild(new Sphere(0.04f, aparienciaRojo));
        extSupIzquierda.addChild(posicionaBrazoITG);
        extSupIzquierda.addChild(antebrazoIDesplazadoTG);
        antebrazoIDesplazadoTG.addChild(moverAntebrazoI);
        moverAntebrazoI.addChild(antebrazoIzquierda);

        posicionaBrazoITG.addChild(new Cylinder(0.03f, 0.04f, aparienciaRojo));

        antebrazoIzquierda.addChild(antebrazoILevantadoTG);
        antebrazoIzquierda.addChild(posicionaManoITG);
        antebrazoIzquierda.addChild(posicionaCodoITG);

        posicionaCodoITG.addChild(new Sphere(0.03f, aparienciaRojo));
        posicionaManoITG.addChild(new Sphere(0.05f, aparienciaBlanco));
        antebrazoILevantadoTG.addChild(new Cylinder(0.03f, 0.045f, aparienciaRojo));
        moverBrazoI.addChild(extSupIzquierda);
        extSupIDesplazadoTG.addChild(moverBrazoI);

        // Rotacion inicial brazo derecho
        Transform3D rotacionInicialbrazoi = new Transform3D();
        rotacionInicialbrazoi.rotZ(Math.PI / 8);
        moverBrazoI.setTransform(rotacionInicialbrazoi);

        // Rotacion inicial antebrazo derecho
        Transform3D rotacionInicialantebrazoi = new Transform3D();
        rotacionInicialantebrazoi.rotZ(-Math.PI / 12);
        moverAntebrazoI.setTransform(rotacionInicialantebrazoi);

        // Estructura
        //**************************************************
        objRoot.addChild(conjuntoTroncoDesplazadoTG);
        objRoot.addChild(extInfIDesplazadoTG);
        objRoot.addChild(extInfDDesplazadoTG);
        objRoot.addChild(extSupDDesplazadoTG);
        objRoot.addChild(extSupIDesplazadoTG);
        return objRoot;
    }

    @Override
    public void actualizar() {
        //Opcional: ACTUALIZACION DEL ESTADO DE LA FIGURA Y DEL ESTADO DEL ENTORNO
        //Para actualizar el estado de la figura:  detectar cercanias,exploraciones picking, localizacion (cuadrantes, mundos)
        //Para actualizar el estado del entorno:  lo puede hacer la misma figura, una figura coordinara, o el mismo juego
        // Movimiento de brazo y piernas
        if (adelante || atras) {
            if (angulo > Math.PI / 4 && dir == 1) {
                dir = -1;
            }
            if (dir == -1 && angulo < -Math.PI / 4) {
                dir = 1;
            }

            angulo = angulo + (dir) * (float) (Math.PI / 15d);
            anguloSecundario = anguloSecundario + (dir) * (float) (Math.PI / 35);
        }

        if (saltando && !enSalto) {
            fuerzaSalto = new Vector3f(0f, 1.5f, 0f);
            enSalto = true;
        } else {
            fuerzaSalto = new Vector3f(0f, 0f, 0f);
            if (!saltando) {
                enSalto = false;
            }

        }
        //Opcional: ACTUALIZACION DE PLANIFICACION A LARGO PLAZAO
        //Dependiendo del objetivo a conseguir ejecutar un plan a largo plazo

        //REGLAS DE MOVIMIENTO A CORTO PLAZO DE LA FIGURA DEPENDIENDO DE SU ESTADO, DEL ENTORNO Y DEL ESTADO DEL JUEGO
        //ejemplo: C—digo de actualizar() del programa  Navegador_Tema_3
        if (localizacionObjetivo != null) {
            Vector3f direccion = new Vector3f(localizacionObjetivo.x - posiciones[0], 0f, localizacionObjetivo.z - posiciones[2]); //localizacionObjetivo.z - posiciones[2]
            direccion.normalize();                                                                           //El vector se normaliza con 1 para que indique solo la direccion.
            Vector3f fuerzaDePersecucion;
            fuerzaDePersecucion = new Vector3f(direccion.x * masa * aceleracionMuscular / 2f, 0, direccion.z * masa * aceleracionMuscular / 2f);  //Crea vector fuerza
            cuerpoRigido.applyCentralForce(fuerzaDePersecucion);
        }
    }

    @Override
    public void mostrar(RigidBody cuerpoRigido) {
        //Actualiacin de posicon y rotacion de la figura visual, en base a la reciente posicion/rotacion del cuerpo rigido
        Transform trans = new Transform();
        if (cuerpoRigido != null && cuerpoRigido.getMotionState() != null) {
            cuerpoRigido.getMotionState().getWorldTransform(trans);
            cuerpoRigido.applyCentralImpulse(fuerzaSalto);
            Quat4f orientacion = new Quat4f();
            cuerpoRigido.getOrientation(orientacion);
            //Transform3D rot = new Transform3D(orientacion, new Vector3f((float) trans.origin.x, (float) trans.origin.y,(float) trans.origin.z), 1); //(float) trans.origin.z
            Transform3D rot = new Transform3D(orientacion, new Vector3f((float) trans.origin.x, (float) trans.origin.y, (float) trans.origin.z), 1);
            desplazamientoFigura.setTransform(rot);

            //Actualizacion de Matriz de rotaci—n y posiciones
            rot.get(this.matrizRotacionPersonaje);
            this.posiciones[0] = trans.origin.x;
            this.posiciones[1] = trans.origin.y;
            this.posiciones[2] = trans.origin.z;
        }
        Transform3D inip = new Transform3D(matrizRotacionPersonaje, new Vector3f(posiciones[0], posiciones[1], posiciones[2]), 1f);
        desplazamientoFigura.setTransform(inip);
        Transform3D rotacionExtInfDer = new Transform3D();
        Transform3D rotacionExtInfIzq = new Transform3D();
        Transform3D rotacionExtSupR = new Transform3D();
        Transform3D rotacionExtSupI = new Transform3D();
        rotacionExtSupR.set(new AxisAngle4f(1f, 0f, 0f, -angulo));
        rotacionExtSupI.set(new AxisAngle4f(1f, 0f, 0f, angulo));
        rotacionExtInfIzq.set(new AxisAngle4f(1f, 0f, 0f, -angulo));
        rotacionExtInfDer.set(new AxisAngle4f(1f, 0f, 0f, angulo));

        moverPiernaD.setTransform(rotacionExtInfDer);
        moverBrazoI.setTransform(rotacionExtSupI);
        moverPiernaI.setTransform(rotacionExtInfIzq);
        moverBrazoD.setTransform(rotacionExtSupR);
    }
}
