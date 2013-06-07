/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Container;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author JuanM
 */
public class Mundo {
    public static BranchGroup  crearMundo(){
        BranchGroup objRoot = new BranchGroup();
        
        
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
        float x,y,z,posx,posy,posz;
        x =1f;
        y = 0.25f;
        z= 2f;
        posx = 0f;
        posy = -2.5f;
        posz = 2f;
        Box escalonVisual1 = new Box(x,y,z, aparienciaAzul);
        Transform3D posicionar = new Transform3D();
        posicionar.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG = new TransformGroup(posicionar);
        posicionarTG.addChild(escalonVisual1);
        
        BoxShape escalonFisico1 = new BoxShape(new Vector3f(x,y,z));
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
        x =1f;
        y = 0.25f;
        z= 1.4f;
        posx = 0f;
        posy = -2f;
        posz = 2.5f;
        Box escalonVisual2 = new Box(x,y,z, aparienciaAzul);
        Transform3D posicionar2 = new Transform3D();
        posicionar2.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG2 = new TransformGroup(posicionar2);
        posicionarTG2.addChild(escalonVisual2);
        
        BoxShape escalonFisico2 = new BoxShape(new Vector3f(x,y,z));
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
        x =1f;
        y = 0.25f;
        z= 1f;
        posx = 0f;
        posy = -1.5f;
        posz = 3f;
        Box escalonVisual3 = new Box(x,y,z, aparienciaAzul);
        Transform3D posicionar3 = new Transform3D();
        posicionar3.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG3 = new TransformGroup(posicionar3);
        posicionarTG3.addChild(escalonVisual3);
        
        BoxShape escalonFisico3 = new BoxShape(new Vector3f(x,y,z));
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
        x =5f;
        y = 1f;
        z= 1f;
        posx = 6f;
        posy = -1.5f;
        posz = 3f;
        Box baseVisual1 = new Box(x,y,z, aparienciaAzul);
        Transform3D posicionar4 = new Transform3D();
        posicionar4.set(new Vector3f(posx, posy, posz));
        TransformGroup posicionarTG4 = new TransformGroup(posicionar4);
        posicionarTG4.addChild(baseVisual1);
        
        BoxShape baseFisica1 = new BoxShape(new Vector3f(x,y,z));
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
        
        //Pendulo1
        Cylinder c = new Cylinder();
        
        // Estructura
        objRoot.addChild(posicionarTG4);
        objRoot.addChild(posicionarTG3);
        objRoot.addChild(posicionarTG2);
        objRoot.addChild(posicionarTG);
        return objRoot;
    }
}
