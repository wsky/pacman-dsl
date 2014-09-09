package pacman.definition;

import pacman.Activity;
import pacman.ActivityLocationReferenceEnvironment;
import pacman.ActivityUtilities;
import pacman.RenderProcessActivityCallback;

public class ActivityTestHelper {
	public static void testMetadata(Activity activity) throws Exception {
		ActivityLocationReferenceEnvironment hostEnvironment = new ActivityLocationReferenceEnvironment(null);
		ActivityUtilities.cacheRootMetadata(activity, hostEnvironment, new RenderProcessActivityCallback());
	}
}
