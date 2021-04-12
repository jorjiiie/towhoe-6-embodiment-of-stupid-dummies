/*

	no bueno enemy spawning function
	Author: Ryan
*/

public class EnemySpawner {
	
	private long start_ns,frame_count;
	private int frames_until_spawn;
	private double last_val=0;
	public EnemySpawner() {
		frames_until_spawn=300;
		start_ns = System.nanoTime();
	}
	public int spawn() {

		frames_until_spawn--;
		frame_count++;
		if (frames_until_spawn<=0) {
			// this is going to be really inaccurate now but this will allow pausing to work properly
			// long current_ns = System.nanoTime() - start_ns;
			// tragedy
			long current_ns = (long)(frame_count*1.0/UserPanel.FRAMERATE*1000000000);
			current_ns/=10;
			double log_x = Math.log(current_ns);

			// weird function but plot in desmos it kinda works lol
			double val = Math.sqrt(current_ns)*Math.pow(log_x,Math.pow(Math.log(log_x-9)-1,1.15))/20000000;

			last_val = val;
			double spawns_per_sec = Math.max(.3,(Math.random() * (val-.3) + .3));

			frames_until_spawn = (int) (UserPanel.FRAMERATE*1.0 / spawns_per_sec); 
			frames_until_spawn = Math.min(frames_until_spawn, 300);

			// spawn code or how many enemies?
			return (int) Math.log((Math.random()*2000*val));

		}
		return 0;
	}
	public double getVal() {
		return last_val;
	}
}