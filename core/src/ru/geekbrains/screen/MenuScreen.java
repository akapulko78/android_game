package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private SpriteBatch batch;
    private Texture img;

    private Vector2 pos;
    private Vector2 v;
    private Vector2 touched;
    private Vector2 buf;


    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        img = new Texture("star.jpg");
        pos = new Vector2(0, 0);
        v = new Vector2(0.5f, 0.3f); //скорость и направление смещения
        touched = new Vector2(0, 0);
        buf = new Vector2(0,0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //очистка экрана
        buf.set(touched);
        if(buf.sub(pos).len() > v.len()){
            pos.add(v);
        } else {
            pos.set(touched);
        }
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //получение вектора по действию пользователя
        touched.set(screenX, Gdx.graphics.getHeight() - screenY);
        //получение вектора от текущей позиции
        // к месту нажатия пользвателем и его укорачивание
        v.set(touched.cpy().sub(pos).setLength(0.5f));
        return false;
    }

}
