package pacman;

public class Asserter {
	public static <T> T getOrThrowIfExtensionMissing(ActivityContext context, Class<T> type) {
		T e = context.getExtension(type);
		if (e == null)
			throw new PacmanException(String.format("extension \"%s\" can not be null", type.getName()));
		return e;
	}
	
	public static PacmanException shouldNotReachHere() {
		return new PacmanException("should not reach here");
	}
}
