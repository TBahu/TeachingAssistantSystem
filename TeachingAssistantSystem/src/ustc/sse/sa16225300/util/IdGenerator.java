package ustc.sse.sa16225300.util;

import java.util.UUID;

public class IdGenerator {
	public static String genPrimaryKey(){
		return UUID.randomUUID().toString();
	}
}
