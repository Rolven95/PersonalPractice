package leetcode;

import java.util.HashMap;

/**
 * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
 * <p>
 * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字  i < j 且有 (time[i] + time[j]) % 60 == 0。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[30,20,150,100,40]
 * 输出：3
 * 解释：这三对的总持续时间可被 60 整数：
 * (time[0] = 30, time[2] = 150): 总持续时间 180
 * (time[1] = 20, time[3] = 100): 总持续时间 120
 * (time[1] = 20, time[4] = 40): 总持续时间 60
 * 示例 2：
 * <p>
 * 输入：[60,60,60]
 * 输出：3
 * 解释：所有三对的总持续时间都是 120，可以被 60 整数。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= time.length <= 60000
 * 1 <= time[i] <= 500
 */
public class MusicDuration {

    public int numPairsDivisibleBy60(int[] time) {
        HashMap<Integer, Integer> modMap = new HashMap<>();
        int counter = 0;
        if (time == null || time.length == 0)
            return 0;
        for (int t : time) {
            int mod = Math.floorMod(t, 60);
            int need = 60 - mod;
            if (need == 60)
                need = 0;

            if (modMap.containsKey(need)) {
                counter = counter + modMap.get(need);
            }

            if (modMap.containsKey(mod)) {
                int exist = modMap.get(mod);
                modMap.put(mod, 1 + exist);
            } else {
                modMap.put(mod, 1);
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        MusicDuration musicDuration = new MusicDuration();
//        int[] test = new int[]{15, 63, 451, 213, 37, 209, 343, 319};
//        int[] test = new int[]{174,188,377,437,54,498,455,239,183,347,59,199,52,488,147,82};
        int[] test = new int[]{1,1,1,1,1};
        musicDuration.numPairsDivisibleBy60(test);
    }
}
