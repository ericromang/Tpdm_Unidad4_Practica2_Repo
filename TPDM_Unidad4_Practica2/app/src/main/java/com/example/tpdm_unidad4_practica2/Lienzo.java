package com.example.tpdm_unidad4_practica2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View {
    Thread hilo;
    String posicion = "";
    Moscas normal,jefe, puntero;

    int contador=60,contadorJefe=10,gano=0;


    public Lienzo(Context context){
        super(context);
        normal = new Moscas(this,R.drawable.mini_mosca,400,1000,30);
        jefe = new Moscas(this,R.drawable.jefe_mosca,60,200,6);
        puntero = null;
        hilo = new Thread() {

            public void run() {
                while (true) {
                    if(gano==1)
                        return;
                    if(normal.vidas>=1){
                        contador--;
                    }else {
                        contadorJefe--;
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        };
        hilo.start();
    }

    protected void onDraw (Canvas c){
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setTextSize(60f);

        if(gano==1 || (jefe.vidas <= 0 && normal.vidas <= 0)){
            c.drawText("Ganaste!!!!!!!", 60, 120, p);
            gano=1;
            return;
        }else if(contador<=0 || contadorJefe<=0) {
            c.drawText("        Perdiste campeon!!", 60, 60, p);
            c.drawText("   no hay boton de reiniciar XDDD", 60, 120, p);
            return;
        }


        if(normal.vidas>=1){
            normal.pintar(c,p,"normal");
            c.drawText("Moscar por matar:"+normal.vidas+"  Tiempo:"+contador,60,60,p);
            c.drawText(normal.x+"  "+normal.y,60,120,p);
        }else {
            jefe.pintar(c, p, "jefe");
            c.drawText("Golpes jefe:"+jefe.vidas+"  Tiempo"+contadorJefe,60,60,p);
        }
    }

    public boolean onTouchEvent(MotionEvent me){
        //el evento ontouchevent permite detectar los toques
        //de uno o mas dedos que se hacen en el area de dibujo
        /*me.getAction() = accion: presiono,soltar,mover
                       pos x pos y*/

        int accion = me.getAction();
        int posx = (int) me.getX();
        int posy = (int) me.getY();

        posicion = posx+" , "+posy;

        switch (accion){
            case MotionEvent.ACTION_DOWN:
                //presiono
                if(normal.estaEnArea(posx,posy) && normal.vidas>=0){
                    normal.mover((int)(Math.random()*(getWidth()-normal.imagen.getWidth())),(int)(Math.random()*(getHeight()-normal.imagen.getHeight())));
                    normal.vidas--;
                }
                if(jefe.estaEnArea(posx,posy) && normal.vidas<=0){
                    jefe.mover((int)(Math.random()*(getWidth()-jefe.imagen.getWidth())),(int)(Math.random()*(getHeight()-jefe.imagen.getHeight())));
                    jefe.vidas--;
                }

                break;
            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:

                break;

        }

        invalidate();
        return true;
    }

}
