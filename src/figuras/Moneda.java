/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

/**
 *
 * @author JuanM
 */
public class Moneda {
    
    public BranchGroup bgmoneda;
    public Vector3f posiciones;
    public TransformGroup rotador;
    
    public Moneda(float posx,float posy, float posz){ 
        
        Transform3D posicionar = new Transform3D();
        posicionar.set(new Vector3f(posx, posy, posz));
        Transform3D rotar = new Transform3D();
        rotar.rotX(Math.PI / 2);
        posicionar.mul(rotar);
        rotador = new TransformGroup(posicionar);
        rotador.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rotador.addChild(bgmoneda);
    }
    
    public void mostrar(){
        Transform3D rotar = new Transform3D();
        rotar.rotY(Math.PI/15d);
        Transform3D principal = new Transform3D();
        rotador.getTransform(principal);
        principal.mul(rotar);
        rotador.setTransform(principal);
        
    }
    
}
