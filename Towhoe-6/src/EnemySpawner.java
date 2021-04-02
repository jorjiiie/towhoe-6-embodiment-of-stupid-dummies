/*

	cancer enemy spawning function
	TODO: PAUSE (not bad just do a subtract for elapsed time during pause)
*/

public class EnemySpawner {
	
	private long start_ns;
	private int frames_until_spawn;

	public EnemySpawner() {
		frames_until_spawn=300;
		start_ns = System.nanoTime();
	}
	public int spawn() {

		frames_until_spawn--;
		if (frames_until_spawn<=0) {
			long current_ns = System.nanoTime() - start_ns;
			current_ns/=10;
			double log_x = Math.log(current_ns);

			// weird function but plot in desmos it kinda works lol
			double val = Math.sqrt(current_ns)*Math.pow(log_x,Math.pow(Math.log(log_x-9)-1,1.15))/20000000;

			double spawns_per_sec = Math.max(.3,(Math.random() * (val-.3) + .3));

			frames_until_spawn = (int) (UserPanel.FRAMERATE*1.0 / spawns_per_sec); 
			frames_until_spawn = Math.min(frames_until_spawn, 300);

			// spawn code or how many enemies?
			return (int) Math.log((Math.random()*2000*val));

		}
		return 0;
	}
	public static void main(String[] args) {
		EnemySpawner j = new EnemySpawner();
		for (int i=0;i<2e9;i++) {
			j.spawn();
			for (int k=0;k<1e6;k++) {
				continue;
			}
		}
	}

	

}