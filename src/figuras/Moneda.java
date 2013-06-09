/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import javax.media.j3d.BranchGroup;
import javax.vecmath.Vector3f;

/**
 *
 * @author olivo
 */
public class Moneda {
    
    private BranchGroup bgmoneda;
    private Vector3f posicion;
    
    
    
    public Moneda(BranchGroup bg, Vector3f v){
        bgmoneda = bg;
        posicion = v;
        
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
    
    
    
}
