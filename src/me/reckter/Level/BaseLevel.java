package me.reckter.Level;

import me.reckter.Entity.Category.CollisionGroup;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Building.Energizer;
import me.reckter.Entity.EntityHandler;
import me.reckter.Entity.Events.*;
import me.reckter.Entity.Logic.*;
import me.reckter.Interface.BaseInterface;
import me.reckter.Interface.HUD.Score;
import me.reckter.Interface.InterfaceHandler;
import me.reckter.Log;
import me.reckter.Particles.BaseParticle;
import me.reckter.Particles.ParticleHandler;
import me.reckter.Player;
import me.reckter.Sound.SoundEngine;
import me.reckter.Tile.TileHandler;
import me.reckter.Util;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by mediacenter on 25.12.13.
 */
public class BaseLevel {


	protected int camX;
	protected int camY;

	protected int fps;

	protected Input input;

	public TileHandler tileHandler;

	protected SoundEngine soundEngine;

	public EntityHandler entities;

	protected BaseLevel nextLevel;

	protected ParticleHandler particles;

	public InterfaceHandler interfaces;

	protected Score score;

	public int HEIGHT = 400;
	public int WIDTH = 640;

	public HashMap<String, Player> players;


	public BaseLevel() {

		this.players = new HashMap<String, Player>();

		this.entities = new EntityHandler(this);

		this.interfaces = new InterfaceHandler();

		this.tileHandler = new TileHandler(this);

		this.particles = new ParticleHandler(this);

		this.score = new Score(this);

		this.nextLevel = null;

		this.soundEngine = new SoundEngine();

		Util.random = new Random(7);
	}

	/**
	 * populates the Level
	 */
	public void populate() {
		Log.info("Populting level...");
		players.put("player", new Player("player", 500, 405));
	}

	/**
	 * gets called bevor every logic tick
	 *
	 * @param input
	 */
	public void processInput(Input input) {
	}

	/**
	 * gets called when Level is initialized
	 */
	public void init() {
		Log.info("initializing level...");

		interfaces.add(score);

		entities.add(new AbilityLogic(this), TickEvent.class);
		entities.add(new BasicLogic(this), TickEvent.class, DeathEvent.class, DamageEvent.class);
		entities.add(new Building(this), TickEvent.class);
		entities.add(new DestroyedOnContact(this), CollisionEvent.class);
		entities.add(new EnemyDeath(this), DeathEvent.class);
		entities.add(new EnergizerLogic(this), TickEvent.class);
		entities.add(new ProjectileLogic(this), DeathEvent.class, CollisionEvent.class);
		entities.add(new TimeToLife(this), TickEvent.class);

		CollisionChecking collision = new CollisionChecking(this);
		collision.addCollisionRule(CollisionGroup.H, CollisionGroup.A);
		collision.addCollisionRule(CollisionGroup.H, CollisionGroup.B);
		collision.addCollisionRule(CollisionGroup.A, CollisionGroup.B);
		collision.addCollisionRule(CollisionGroup.B, CollisionGroup.A);

		collision.dellCollisionRule(CollisionGroup.B, CollisionGroup.B);
		collision.dellCollisionRule(CollisionGroup.A, CollisionGroup.A);
		collision.dellCollisionRule(CollisionGroup.H, CollisionGroup.H);

		entities.add(collision, TickOnceEvent.class, EntityAddEvent.class, EntityDelEvent.class);

		entities.updateEntityList();
		entities.logicHandler.updateLogics();
	}

	/**
	 * ads an entity to the level
	 *
	 * @param entity
	 */
	public void add(BaseEntity entity) {
		entities.add(entity);
	}

	/**
	 * adds an interface
	 *
	 * @param face the interface to be added
	 */
	public void add(BaseInterface face) {
		interfaces.add(face);
	}

	public void fire(BaseEvent event) {
		entities.logicHandler.fireEvent(event);
	}


	/**
	 * adds a particle
	 *
	 * @param particle the interface to be added
	 */
	public void add(BaseParticle particle) {
		particles.add(particle);
	}


	/**
	 * delets an entity
	 *
	 * @param entity the entity to be deleted
	 */
	public void del(BaseEntity entity) {
		entities.del(entity);
	}


	/**
	 * delets a particle
	 *
	 * @param particle the entity to be deleted
	 */
	public void del(BaseParticle particle) {
		particles.del(particle);
	}

	/**
	 * delets an Interface
	 *
	 * @param face the particle to be deleted
	 */
	public void del(BaseInterface face) {
		interfaces.del(face);
	}


	/**
	 * gets called every logic tick
	 *
	 * @param delta the time in ms since the last logic tick
	 */
	public void logic(int delta) {

		particles.logic(delta);

		entities.logic(delta);

		interfaces.logic(delta);
	}

	/**
	 * gets called evey render
	 *
	 * @param g the graphics object on which should be drawn
	 */
	public void render(Graphics g) {

		g.translate(camX, camY);

		particles.render(g);

		entities.render(g);

		interfaces.render(g);
	}

	/**
	 * returns the X position on the field (from an x position on the screen)
	 *
	 * @param x
	 * @return
	 */
	public float getRealX(float x) {
		return x + (-camX);
	}

	/**
	 * returns the X position on the Screen (from an x position on the field)
	 *
	 * @param x
	 * @return
	 */
	public float getScreenX(float x) {
		return x - (-camX);
	}

	/**
	 * returns the Y position on the field (from an y position on the screen)
	 *
	 * @param y
	 * @return
	 */
	public float getRealY(float y) {
		return y + (-camY);
	}

	/**
	 * returns the Y position on the Screen (from an y position on the field)
	 *
	 * @param y
	 * @return
	 */
	public float getScreenY(float y) {
		return y - (-camY);
	}


	public ArrayList<BaseEntity> getEntities() {
		return entities.entities;
	}

	public void setEntities(ArrayList<BaseEntity> entities) {
		this.entities.entities = entities;
	}

	public ParticleHandler getParticles() {
		return particles;
	}

	public void setParticles(ParticleHandler particles) {
		this.particles = particles;
	}

	public int getCamX() {
		return camX;
	}

	public void setCamX(int camX) {
		this.camX = camX;
	}

	public int getCamY() {
		return camY;
	}

	public void setCamY(int camY) {
		this.camY = camY;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public BaseLevel getNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(BaseLevel nextLevel) {
		this.nextLevel = nextLevel;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public SoundEngine getSoundEngine() {
		return soundEngine;
	}

	public void setSoundEngine(SoundEngine soundEngine) {
		this.soundEngine = soundEngine;
	}

}

