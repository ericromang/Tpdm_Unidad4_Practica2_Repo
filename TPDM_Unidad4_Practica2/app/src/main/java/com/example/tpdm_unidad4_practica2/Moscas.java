package com.example.tpdm_unidad4_practica2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;

public class Moscas {
    Bitmap imagen;
    int x, y, x1, x2,y1,y2,vidas;

    public Moscas(Lienzo este,int imagen, int posx, int posy,int vidas){
        this.imagen = BitmapFactory.decodeResource(este.getResources(),imagen);
        x = posx;
        y = posy;
        this.vidas = vidas;


    }

    public void mover(int dedox,int dedoy){
        x = dedox ;
        y = dedoy ;

    }

    public void pintar(Canvas c, Paint p,String n){
        if(n.equals("jefe"))
            c.drawBitmap(imagen,x,y,p);
        else
            c.drawBitmap(imagen,x,y,p);
    }

    public boolean estaEnArea(int dedox, int dedoy) {
        int x2 = x + imagen.getWidth();
        int y2 = y + imagen.getHeight();

        if (dedox > x1 && dedox <= x2) {
            if (dedoy >= y1 && dedoy <= y2) {
                return true;
            }

        }
        return false;
    }



}
