/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CylinderShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import figuras.Moneda;
import java.awt.Container;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author JuanM
 */
public class Mundo {

    //MIOINICIO-----------
    public static BranchGroup bgRaizMonedas = new BranchGroup();
    public static Moneda[] listaMonedas;
    
    public static BranchGroup raizEstrella = new BranchGroup();
    public static BranchGroup estrella = new BranchGroup();

    //MIOFIN-----------
    public static BranchGroup crearMundo() {
        BranchGroup objRoot = new BranchGroup();

        Appearance apariencia = new Appearance();
        apariencia.setTexture(new TextureLoader(System.getProperty("user.dir") + "//Bloque_Ladrillo.png", new Container()).getTexture());
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        apariencia.setTextureAttributes(texAttr);




        ColoringAttributes colorAzul = new ColoringAttributes(0f, 0f, 1f, ColoringAttributes.FASTEST);
        Appearance aparienciaAzul = new Appearance();
        aparienciaAzul.setColoringAttributes(colorAzul);

        ColoringAttributes colorBlanco = new ColoringAttributes(1f, 1f, 1f, ColoringAttributes.FASTEST);
        Appearance aparienciaBlanca = new Appearance();
        aparienciaBlanca.setColoringAttributes(colorBlanco);

        /*Texture tex = new TextureLoader(System.getProperty("user.dir") + "\\suelo.jpg", new Container()).getTexture();
         Appearance apariencia = new Appearance();
         apariencia.setTexture(tex);*/

        // Escalon 1
        float x, y, z, posx, posy, posz;
        x = 1f;
        y = 0.25f;
        z = 2f;
        posx = 0f;
        posy = -2.5f;
        posz = 2f;
        Box escalonVisual1 = new Box(x, y, z, Box.GENERATE_TEXTURE_COORDS, apariencia);
        Transform3D posicionar = new Transform3D();
        posicionar.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG = new TransformGroup(posicionar);
        posicionarTG.addChild(escalonVisual1);

        BoxShape escalonFisico1 = new BoxShape(new Vector3f(x, y, z));
        CollisionObject ramaFisica = new CollisionObject();
        ramaFisica.setCollisionShape(escalonFisico1);
        Transform groundTransform = new Transform();
        groundTransform.setIdentity();
        groundTransform.origin.set(new Vector3f(posx, posy, posz));
        Vector3f inerciaLocal = new Vector3f(0, 0, 0);
        DefaultMotionState EstadoDeMovimiento = new DefaultMotionState(groundTransform);
        RigidBodyConstructionInfo InformacionCuerpoR = new RigidBodyConstructionInfo(0f, EstadoDeMovimiento, escalonFisico1, inerciaLocal);
        RigidBody cuerpoRigido = new RigidBody(InformacionCuerpoR);
        cuerpoRigido.setActivationState(RigidBody.DISABLE_DEACTIVATION);
        Juego.mundoFisico.addRigidBody(cuerpoRigido); // add the body to the dynamics world

        // Escalon 2
        x = 1f;
        y = 0.25f;
        z = 1.4f;
        posx = 0f;
        posy = -2f;
        posz = 2.5f;
        Box escalonVisual2 = new Box(x, y, z, Box.GENERATE_TEXTURE_COORDS, apariencia);
        Transform3D posicionar2 = new Transform3D();
        posicionar2.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG2 = new TransformGroup(posicionar2);
        posicionarTG2.addChild(escalonVisual2);

        BoxShape escalonFisico2 = new BoxShape(new Vector3f(x, y, z));
        CollisionObject ramaFisica2 = new CollisionObject();
        ramaFisica2.setCollisionShape(escalonFisico2);
        Transform groundTransform2 = new Transform();
        groundTransform2.setIdentity();
        groundTransform2.origin.set(new Vector3f(posx, posy, posz));
        DefaultMotionState EstadoDeMovimiento2 = new DefaultMotionState(groundTransform2);
        RigidBodyConstructionInfo InformacionCuerpoR2 = new RigidBodyConstructionInfo(0f, EstadoDeMovimiento2, escalonFisico2, inerciaLocal);
        RigidBody cuerpoRigido2 = new RigidBody(InformacionCuerpoR2);
        cuerpoRigido2.setActivationState(RigidBody.DISABLE_DEACTIVATION);
        Juego.mundoFisico.addRigidBody(cuerpoRigido2); // add the body to the dynamics world

        // Escalon 3
        x = 1f;
        y = 0.25f;
        z = 1f;
        posx = 0f;
        posy = -1.5f;
        posz = 3f;
        Box escalonVisual3 = new Box(x, y, z, Box.GENERATE_TEXTURE_COORDS, apariencia);
        Transform3D posicionar3 = new Transform3D();
        posicionar3.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG3 = new TransformGroup(posicionar3);
        posicionarTG3.addChild(escalonVisual3);

        BoxShape escalonFisico3 = new BoxShape(new Vector3f(x, y, z));
        CollisionObject ramaFisica3 = new CollisionObject();
        ramaFisica3.setCollisionShape(escalonFisico3);
        Transform groundTransform3 = new Transform();
        groundTransform3.setIdentity();
        groundTransform3.origin.set(new Vector3f(posx, posy, posz));
        DefaultMotionState EstadoDeMovimiento3 = new DefaultMotionState(groundTransform3);
        RigidBodyConstructionInfo InformacionCuerpoR3 = new RigidBodyConstructionInfo(0f, EstadoDeMovimiento3, escalonFisico3, inerciaLocal);
        RigidBody cuerpoRigido3 = new RigidBody(InformacionCuerpoR3);
        cuerpoRigido3.setActivationState(RigidBody.DISABLE_DEACTIVATION);
        Juego.mundoFisico.addRigidBody(cuerpoRigido3); // add the body to the dynamics world

        //Base1
        x = 1f;
        y = 1f;
        z = 1f;
        posx = 3f;
        posy = -1.5f;
        posz = 3f;
        Box baseVisual1 = new Box(x, y, z, Box.GENERATE_TEXTURE_COORDS, apariencia);
        Transform3D posicionar4 = new Transform3D();
        posicionar4.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG4 = new TransformGroup(posicionar4);
        posicionarTG4.addChild(baseVisual1);

        BoxShape baseFisica1 = new BoxShape(new Vector3f(x, y, z));
        CollisionObject ramaFisica4 = new CollisionObject();
        ramaFisica4.setCollisionShape(baseFisica1);
        Transform groundTransform4 = new Transform();
        groundTransform4.setIdentity();
        groundTransform4.origin.set(new Vector3f(posx, posy, posz));
        DefaultMotionState EstadoDeMovimiento4 = new DefaultMotionState(groundTransform4);
        RigidBodyConstructionInfo InformacionCuerpoR4 = new RigidBodyConstructionInfo(0f, EstadoDeMovimiento4, baseFisica1, inerciaLocal);
        RigidBody cuerpoRigido4 = new RigidBody(InformacionCuerpoR4);
        cuerpoRigido4.setActivationState(RigidBody.DISABLE_DEACTIVATION);
        Juego.mundoFisico.addRigidBody(cuerpoRigido4); // add the body to the dynamics world

        //Base2
        x = 1f;
        y = 1f;
        z = 1f;
        posx = 5f;
        posy = -1.5f;
        posz = 8f;
        Box baseVisual2 = new Box(x, y, z, Box.GENERATE_TEXTURE_COORDS, apariencia);
        Transform3D posicionar5 = new Transform3D();
        posicionar5.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG5 = new TransformGroup(posicionar5);
        posicionarTG5.addChild(baseVisual2);

        BoxShape baseFisica2 = new BoxShape(new Vector3f(x, y, z));
        CollisionObject ramaFisica5 = new CollisionObject();
        ramaFisica5.setCollisionShape(baseFisica2);
        Transform groundTransform5 = new Transform();
        groundTransform5.setIdentity();
        groundTransform5.origin.set(new Vector3f(posx, posy, posz));
        DefaultMotionState EstadoDeMovimiento5 = new DefaultMotionState(groundTransform5);
        RigidBodyConstructionInfo InformacionCuerpoR5 = new RigidBodyConstructionInfo(0f, EstadoDeMovimiento5, baseFisica2, inerciaLocal);
        RigidBody cuerpoRigido5 = new RigidBody(InformacionCuerpoR5);
        cuerpoRigido5.setActivationState(RigidBody.DISABLE_DEACTIVATION);
        Juego.mundoFisico.addRigidBody(cuerpoRigido5); // add the body to the dynamics world

        //Base3
        x = 1f;
        y = 1.7f;
        z = 1f;
        posx = 9f;
        posy = -1.5f;
        posz = 10f;
        Box baseVisual3 = new Box(x, y, z, Box.GENERATE_TEXTURE_COORDS, apariencia);
        Transform3D posicionar6 = new Transform3D();
        posicionar6.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG6 = new TransformGroup(posicionar6);
        posicionarTG6.addChild(baseVisual3);

        BoxShape baseFisica3 = new BoxShape(new Vector3f(x, y, z));
        CollisionObject ramaFisica6 = new CollisionObject();
        ramaFisica6.setCollisionShape(baseFisica3);
        Transform groundTransform6 = new Transform();
        groundTransform6.setIdentity();
        groundTransform6.origin.set(new Vector3f(posx, posy, posz));
        DefaultMotionState EstadoDeMovimiento6 = new DefaultMotionState(groundTransform6);
        RigidBodyConstructionInfo InformacionCuerpoR6 = new RigidBodyConstructionInfo(0f, EstadoDeMovimiento6, baseFisica3, inerciaLocal);
        RigidBody cuerpoRigido6 = new RigidBody(InformacionCuerpoR6);
        cuerpoRigido6.setActivationState(RigidBody.DISABLE_DEACTIVATION);
        Juego.mundoFisico.addRigidBody(cuerpoRigido6); // add the body to the dynamics world

        //Base4
        x = 1f;
        y = 2.1f;
        z = 1f;
        posx = 12f;
        posy = -1.5f;
        posz = 8f;
        Box baseVisual4 = new Box(x, y, z, Box.GENERATE_TEXTURE_COORDS, apariencia);
        Transform3D posicionar7 = new Transform3D();
        posicionar7.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG7 = new TransformGroup(posicionar7);
        posicionarTG7.addChild(baseVisual4);

        BoxShape baseFisica4 = new BoxShape(new Vector3f(x, y, z));
        CollisionObject ramaFisica7 = new CollisionObject();
        ramaFisica7.setCollisionShape(baseFisica4);
        Transform groundTransform7 = new Transform();
        groundTransform7.setIdentity();
        groundTransform7.origin.set(new Vector3f(posx, posy, posz));
        DefaultMotionState EstadoDeMovimiento7 = new DefaultMotionState(groundTransform7);
        RigidBodyConstructionInfo InformacionCuerpoR7 = new RigidBodyConstructionInfo(0f, EstadoDeMovimiento7, baseFisica4, inerciaLocal);
        RigidBody cuerpoRigido7 = new RigidBody(InformacionCuerpoR7);
        cuerpoRigido7.setActivationState(RigidBody.DISABLE_DEACTIVATION);
        Juego.mundoFisico.addRigidBody(cuerpoRigido7); // add the body to the dynamics world

        //Meta
        x = 1f;
        y = 6f;

        posx = 12f;
        posy = -1.5f;
        posz = -4f;
        Cylinder baseVisual5 = new Cylinder(x, y, Cylinder.GENERATE_TEXTURE_COORDS, apariencia);
        Transform3D posicionar8 = new Transform3D();
        posicionar8.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG8 = new TransformGroup(posicionar8);
        posicionarTG8.addChild(baseVisual5);

        CylinderShape baseFisica5 = new CylinderShape(new Vector3f(x, y / 2f, 0));
        CollisionObject ramaFisica8 = new CollisionObject();
        ramaFisica8.setCollisionShape(baseFisica5);
        Transform groundTransform8 = new Transform();
        groundTransform8.setIdentity();
        groundTransform8.origin.set(new Vector3f(posx, posy, posz));
        DefaultMotionState EstadoDeMovimiento8 = new DefaultMotionState(groundTransform8);
        RigidBodyConstructionInfo InformacionCuerpoR8 = new RigidBodyConstructionInfo(0f, EstadoDeMovimiento8, baseFisica5, inerciaLocal);
        RigidBody cuerpoRigido8 = new RigidBody(InformacionCuerpoR8);
        cuerpoRigido8.setActivationState(RigidBody.DISABLE_DEACTIVATION);
        Juego.mundoFisico.addRigidBody(cuerpoRigido8); // add the body to the dynamics world



        //---MIOINICIO

        generarEstrella(objRoot);
        
        //Array donde cada valor es la posición de un árbol
        Vector3f[] v = new Vector3f[16];
        v[0] = new Vector3f(2f, -2f, 0f);
        v[1] = new Vector3f(4.5f, -2f, 0f);
        v[2] = new Vector3f(4.5f, -2f, -2f);

        v[3] = new Vector3f(1f, -2f, -6f);
        v[4] = new Vector3f(-3f, -2f, -6f);
        v[5] = new Vector3f(4.5f, -2f, -7f);
        v[6] = new Vector3f(7f, -2f, -10f);

        v[7] = new Vector3f(-7f, -2f, -10f);
        v[8] = new Vector3f(-5f, -2f, -12f);
        v[9] = new Vector3f(-5f, -2f, 13f);

        v[10] = new Vector3f(-4f, -2f, 9f);
        v[11] = new Vector3f(0f, -2f, 12f);
        v[12] = new Vector3f(5f, -2f, 14f);

        v[13] = new Vector3f(9f, -2f, 15f);
        v[14] = new Vector3f(18f, -2f, 15f);
        v[15] = new Vector3f(18f, -2f, 6f);


        colocarArboles(v, objRoot);

        Vector3f[] v2 = new Vector3f[4];
        v2[0] = new Vector3f(2f, -1f, 8f);
        v2[1] = new Vector3f(1f, -1f, -5f);
        v2[2] = new Vector3f(14f, -1f, -5f);
        v2[3] = new Vector3f(-4.5f, -1f, -1f);
        colocarCajas(v2, objRoot);

        Vector3f[] v3 = new Vector3f[10];
        v3[0] = new Vector3f(2f, -2.5f, -2f);
        v3[1] = new Vector3f(-3f, -2.5f, -2f);
        v3[2] = new Vector3f(2f, -2.5f, -2.5f);
        v3[3] = new Vector3f(0f, -0.5f, 2.7f);
        v3[4] = new Vector3f(1f, -2.5f, -1f);
        v3[5] = new Vector3f(3f, -0.1f, 3f);
        v3[6] = new Vector3f(5f, -0.1f, 8f);
        v3[7] = new Vector3f(1f, -2.5f, -2f);
        v3[8] = new Vector3f(9f, 0.6f, 10f);
        v3[9] = new Vector3f(12f, 0.9f, 8f);
        colocarMonedas(v3, objRoot);
        //generarMonedas(v3[4], objRoot);


        //-----MIOFIN


        // Estructura
        
        objRoot.addChild(posicionarTG8);
        objRoot.addChild(posicionarTG7);
        objRoot.addChild(posicionarTG6);
        objRoot.addChild(posicionarTG5);
        objRoot.addChild(posicionarTG4);
        objRoot.addChild(posicionarTG3);
        objRoot.addChild(posicionarTG2);
        objRoot.addChild(posicionarTG);
        return objRoot;
    }

    //----MIOINICIO
    public static void colocarArboles(Vector3f[] posiciones, BranchGroup objRoot) {

        for (int i = 0; i < posiciones.length; i++) {

            generarArboles(posiciones[i], objRoot);

        }

    }

    public static void generarArboles(Vector3f posicion, BranchGroup objRoot) {


        Appearance apariencia = new Appearance();
        apariencia.setTexture(new TextureLoader(System.getProperty("user.dir") + "//corteza_arbol.jpg", new Container()).getTexture());
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        apariencia.setTextureAttributes(texAttr);


        //TRONCO
        float x = 0.14f;
        float y = 1.5f;

        float posx = posicion.x;
        float posy = posicion.y, posz = posicion.z;
        Cylinder tronco = new Cylinder(x, y, Cylinder.GENERATE_TEXTURE_COORDS, apariencia);
        Transform3D posicionar = new Transform3D();
        posicionar.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG = new TransformGroup(posicionar);
        posicionarTG.addChild(tronco);

        CylinderShape baseFisica = new CylinderShape(new Vector3f(x, y / 2f, 0));
        CollisionObject ramaFisica = new CollisionObject();
        ramaFisica.setCollisionShape(baseFisica);
        Transform groundTransform = new Transform();
        groundTransform.setIdentity();
        groundTransform.origin.set(new Vector3f(posx, posy, posz));
        Vector3f inerciaLocal = new Vector3f(0, 0, 0);
        DefaultMotionState EstadoDeMovimiento = new DefaultMotionState(groundTransform);
        RigidBodyConstructionInfo InformacionCuerpoR = new RigidBodyConstructionInfo(0f,
                EstadoDeMovimiento, baseFisica, inerciaLocal);
        RigidBody cuerpoRigido = new RigidBody(InformacionCuerpoR);
        cuerpoRigido.setActivationState(RigidBody.DISABLE_DEACTIVATION);
        Juego.mundoFisico.addRigidBody(cuerpoRigido); // add the body to the dynamics world


        //RAMAS

        Appearance apariencia2 = new Appearance();
        apariencia2.setTexture(new TextureLoader(System.getProperty("user.dir") + "//hojas_arbol.jpg", new Container()).getTexture());
        TextureAttributes texAttr2 = new TextureAttributes();
        texAttr2.setTextureMode(TextureAttributes.MODULATE);
        apariencia2.setTextureAttributes(texAttr2);

        float radio = 0.75f;

        float posxEsfera = posicion.x;
        float posyEsfera = posicion.y + 1.3f, poszEsfera = posicion.z;
        Sphere esfera = new Sphere(radio, Sphere.GENERATE_TEXTURE_COORDS, apariencia2);
        Transform3D posicionarEsfera = new Transform3D();
        posicionarEsfera.set(new Vector3f(posxEsfera, posyEsfera, poszEsfera));
        TransformGroup posicionarTGEsfera = new TransformGroup(posicionarEsfera);
        posicionarTGEsfera.addChild(esfera);

        SphereShape baseFisicaEsfera = new SphereShape(radio);
        CollisionObject ramaFisicaEsfera = new CollisionObject();
        ramaFisicaEsfera.setCollisionShape(baseFisicaEsfera);
        Transform groundTransformEsfera = new Transform();
        groundTransformEsfera.setIdentity();
        groundTransformEsfera.origin.set(new Vector3f(posxEsfera, posyEsfera, poszEsfera));
        DefaultMotionState EstadoDeMovimientoEsfera = new DefaultMotionState(groundTransformEsfera);
        RigidBodyConstructionInfo InformacionCuerpoREsfera = new RigidBodyConstructionInfo(0f,
                EstadoDeMovimientoEsfera, baseFisicaEsfera, inerciaLocal);
        RigidBody cuerpoRigidoEsfera = new RigidBody(InformacionCuerpoREsfera);
        cuerpoRigidoEsfera.setActivationState(RigidBody.DISABLE_DEACTIVATION);
        Juego.mundoFisico.addRigidBody(cuerpoRigidoEsfera); // add the body to the dynamics world




        objRoot.addChild(posicionarTGEsfera);
        objRoot.addChild(posicionarTG);

    }
    //---MIOFIN

    //----MIOINICIO
    public static void colocarCajas(Vector3f[] posiciones, BranchGroup objRoot) {

        for (int i = 0; i < posiciones.length; i++) {

            generarCajas(posiciones[i], objRoot);

        }

    }

    public static void generarCajas(Vector3f posicion, BranchGroup objRoot) {


        Appearance apariencia = new Appearance();
        apariencia.setTexture(new TextureLoader(System.getProperty("user.dir") + "//caja_coin.jpg", new Container()).getTexture());
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        apariencia.setTextureAttributes(texAttr);


        float x = 0.3f;
        float y = 0.3f;
        float z = 0.3f;

        float posx = posicion.x;
        float posy = posicion.y, posz = posicion.z;

        Box escalonVisual1 = new Box(x, y, z, Box.GENERATE_TEXTURE_COORDS, apariencia);
        Transform3D posicionar = new Transform3D();
        posicionar.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG = new TransformGroup(posicionar);
        posicionarTG.addChild(escalonVisual1);

        BoxShape escalonFisico1 = new BoxShape(new Vector3f(x, y, z));
        CollisionObject ramaFisica = new CollisionObject();
        ramaFisica.setCollisionShape(escalonFisico1);
        Transform groundTransform = new Transform();
        groundTransform.setIdentity();
        groundTransform.origin.set(new Vector3f(posx, posy, posz));
        Vector3f inerciaLocal = new Vector3f(0, 0, 0);
        DefaultMotionState EstadoDeMovimiento = new DefaultMotionState(groundTransform);
        RigidBodyConstructionInfo InformacionCuerpoR = new RigidBodyConstructionInfo(0f, EstadoDeMovimiento, escalonFisico1, inerciaLocal);
        RigidBody cuerpoRigido = new RigidBody(InformacionCuerpoR);
        cuerpoRigido.setActivationState(RigidBody.DISABLE_DEACTIVATION);
        Juego.mundoFisico.addRigidBody(cuerpoRigido); // add the body to the dynamics world



        objRoot.addChild(posicionarTG);

    }
    //---MIOFIN

    //----MIOINICIO
    public static void colocarMonedas(Vector3f[] posiciones, BranchGroup objRoot) {

        listaMonedas = new Moneda[posiciones.length];
        for (int i = 0; i < posiciones.length; i++) {

            listaMonedas[i] = generarMonedas(posiciones[i], objRoot);

        }
        //añadimos la raiz de monedas al objRoot
        objRoot.addChild(bgRaizMonedas);

    }

    public static Moneda generarMonedas(Vector3f posicion, BranchGroup objRoot) {


        Appearance apariencia = new Appearance();
        apariencia.setTexture(new TextureLoader(System.getProperty("user.dir") + "//retro_coin.png", new Container()).getTexture());
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        apariencia.setTextureAttributes(texAttr);


        float x = 0.14f;
        float y = 0.05f;

        float posx = posicion.x;
        float posy = posicion.y, posz = posicion.z;


        BranchGroup bgMoneda = new BranchGroup();
        bgMoneda.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        bgMoneda.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        bgMoneda.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        bgMoneda.setCapability(BranchGroup.ALLOW_DETACH);


        bgRaizMonedas.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        bgRaizMonedas.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        bgRaizMonedas.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        bgRaizMonedas.setCapability(BranchGroup.ALLOW_DETACH);


        Transform3D tfMoneda = new Transform3D();
        tfMoneda.set(new Vector3f(posx, posy, posz));
        Transform3D rotar = new Transform3D();
        rotar.rotX(Math.PI / 2);
        tfMoneda.mul(rotar);

        TransformGroup tgMoneda;
        tgMoneda = new TransformGroup(tfMoneda);
        tgMoneda.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        bgMoneda.addChild(tgMoneda);



        tgMoneda.addChild(new Cylinder(x, y, Cylinder.GENERATE_TEXTURE_COORDS, apariencia));

        bgRaizMonedas.addChild(bgMoneda);



        //creamos el objeto moneda que guarda los datos
        return new Moneda(bgMoneda, new Vector3f(posx, posy, posz), tgMoneda);


    }
    
    
    public static void generarEstrella(BranchGroup objRoot){
                  
        
        
        Appearance apariencia = new Appearance();
        apariencia.setTexture(new TextureLoader(System.getProperty("user.dir") + "//Estrella.png", new Container()).getTexture());
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        apariencia.setTextureAttributes(texAttr);


        float x = 0.5f;
        float y = 0.2f;

        float posx = 12.34f;
        float posy = 2f, posz = -4f;


        estrella.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        estrella.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        estrella.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        estrella.setCapability(BranchGroup.ALLOW_DETACH);


        raizEstrella.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        raizEstrella.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        raizEstrella.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        raizEstrella.setCapability(BranchGroup.ALLOW_DETACH);


        Transform3D tfEstrella = new Transform3D();
        tfEstrella.set(new Vector3f(posx, posy, posz));
        Transform3D rotar = new Transform3D();
        rotar.rotX(Math.PI / 2);
        tfEstrella.mul(rotar);

        TransformGroup tgEstrella;
        tgEstrella = new TransformGroup(tfEstrella);
        tgEstrella.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        estrella.addChild(tgEstrella);



        tgEstrella.addChild(new Cylinder(x, y, Cylinder.GENERATE_TEXTURE_COORDS, apariencia));

        
        objRoot.addChild(raizEstrella);
    }
    
    //---MIOFIN
}
