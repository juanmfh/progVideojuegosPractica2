/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Container;
import java.util.ArrayList;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import simulador.Figura;
import simulador.Juego;

/**
 *
 * @author JuanM
 */
public class FiguraBase extends Figura{
    
    TransformGroup movimientoPenduloTG;
    float desp = 0.01f;
    int dir = 1;
    Vector3f fuerzaY;
    public FiguraBase(Vector3f dimensiones,BranchGroup conjunto, ArrayList<Figura> listaObjetosFisicos, Juego juego){
        super(conjunto, listaObjetosFisicos, juego);
        esMDL = false;
        
        
        
        TransformGroup figuraVisual = crearFiguraBase();
        BoxShape figuraFisica = new BoxShape(dimensiones);
        ramaFisica = new CollisionObject();
        ramaFisica.setCollisionShape(figuraFisica);
        ramaVisible.addChild(desplazamientoFigura);
        desplazamientoFigura.addChild(figuraVisual);
        
    }

    private TransformGroup crearFiguraBase() {
        /*BranchGroup figura = crearBranchGroupFigura();
        
        Transform3D rotacionCombinada = new Transform3D();
        TransformGroup rotadorFigura = new TransformGroup(rotacionCombinada);
        rotadorFigura.addChild(figura);*
        */
        
        Appearance apariencia = new Appearance();
        apariencia.setTexture(new TextureLoader(System.getProperty("user.dir") + "//Bloque_Ladrillo.png", new Container()).getTexture());
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        apariencia.setTextureAttributes(texAttr);
        
        
        ColoringAttributes colorAzul = new ColoringAttributes(0f, 0f, 1f, ColoringAttributes.FASTEST);
        Appearance aparienciaAzul = new Appearance ();
        aparienciaAzul.setColoringAttributes(colorAzul);
        
        
         Cylinder c = new Cylinder(0.05f,0.5f,aparienciaAzul);
        Transform3D posicionaC = new Transform3D();
        posicionaC.set(new Vector3f(0f, -0.25f, 0f));
        TransformGroup posicionaCTG = new TransformGroup(posicionaC);
        posicionaCTG.addChild(c);
        
        Cylinder c2 = new Cylinder(0.05f,0.5f,aparienciaAzul);
        Transform3D posicionaC2 = new Transform3D();
        posicionaC2.set(new Vector3f(0f, -0.5f, 0f));
        Transform3D desplazaC2 = new Transform3D();
        desplazaC2.rotZ(Math.PI/2);
        posicionaC2.mul(desplazaC2);
        TransformGroup posicionaC2TG = new TransformGroup(posicionaC2);
        posicionaC2TG.addChild(c2);
        
        BranchGroup pendulo = new BranchGroup();
        pendulo.addChild(posicionaC2TG);
        pendulo.addChild(posicionaCTG);
        
        
        movimientoPenduloTG = new TransformGroup();
        movimientoPenduloTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        movimientoPenduloTG.addChild(new Box(1f,0.2f,1f,Box.GENERATE_TEXTURE_COORDS,apariencia));
        return movimientoPenduloTG;
    }

    
    @Override
    public void actualizar(){
        if(posiciones[1]<0.2f && dir==-1){
            //dir = 1;
            //fuerzaY = new Vector3f(0);
        }else if(posiciones[1]>1.2f && dir==1){
            //dir = -1;
        }
    }
    
    @Override
    public void mostrar(RigidBody cuerpoRigido){
        Transform trans = new Transform();
        if (cuerpoRigido != null && cuerpoRigido.getMotionState() != null) {
            cuerpoRigido.getMotionState().getWorldTransform(trans);
            cuerpoRigido.applyCentralImpulse(new Vector3f(0f,7.6f,0f));
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
        /*Transform3D principal = new Transform3D();
        Transform3D mov = new Transform3D();
        mov.set(new Vector3f(0f,dir*desp,0f));
        movimientoPenduloTG.getTransform(principal);
        principal.mul(mov);
        movimientoPenduloTG.setTransform(principal);*/
    }
}
