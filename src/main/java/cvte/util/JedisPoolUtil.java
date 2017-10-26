package cvte.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class JedisPoolUtil {
          
         private static volatile JedisPool jedisPool = null;//被volatile修饰的变量不会被本地线程缓存，对该变量的读写都是直接操作共享内存。
          
         private JedisPoolUtil() {}

        //Redis服务器IP
        private static String ADDR = "127.0.0.1";
        //Redis的端口号
        private static int PORT = 6379;

        //可用连接实例的最大数目，默认值为8；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        private static int MAX_TOTAL = 1024;

        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
        private static int MAX_IDLE = 32;

        //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
        private static long MAX_WAIT = 10000;

        private static int TIMEOUT = 10000;

        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        private static boolean TEST_ON_BORROW = true;
              public static JedisPool getJedisPoolInstance()
             {
                 if(null == jedisPool)
                {
                   synchronized (JedisPoolUtil.class)
                  {
                      if(null == jedisPool)
                     {
                       JedisPoolConfig poolConfig = new JedisPoolConfig();
                       poolConfig.setMaxTotal(MAX_TOTAL);
                       poolConfig.setMaxIdle(MAX_IDLE);
                       poolConfig.setMaxWaitMillis(MAX_WAIT);
                       poolConfig.setTestOnBorrow(TEST_ON_BORROW);
                       jedisPool = new JedisPool(poolConfig,ADDR,PORT,TIMEOUT);
                     }
                  }
                }
                 return jedisPool;
             }

          public static void release(JedisPool jedisPool,Jedis jedis)
         {
             if(null != jedis)
            {
              jedisPool.returnResourceObject(jedis);
            }
         }
}
