/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author olivo
 */
public class Moneda {
    
    private BranchGroup bgmoneda;
    private Vector3f posicion;
    private TransformGroup rotador;
    private boolean cogida = false;
    
    
    public Moneda(BranchGroup bg, Vector3f v,TransformGroup rotador){
        bgmoneda = bg;
        posicion = v;
        this.rotador = rotador;
    }
    
    public void mostrar(){
        Transform3D rotar = new Transform3D();
        rotar.rotZ(Math.PI/15d);
        Transform3D principal = new Transform3D();
        rotador.getTransform(principal);
        principal.mul(rotar);
        rotador.setTransform(principal);
        
    }

    public BranchGroup getBgmoneda() {
        return bgmoneda;
    }

    public void setBgmoneda(BranchGroup bgmoneda) {
        this.bgmoneda = bgmoneda;
    }

    public Vector3f getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector3f posicion) {
        this.posicion = posicion;
    }

    public boolean isCogida() {
        return cogida;
    }

    public void setCogida(boolean cogida) {
        this.cogida = cogida;
    }
    
    
    
}
