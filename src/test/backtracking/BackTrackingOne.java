package test.backtracking;

import org.junit.Test;

import java.util.*;

/**
 * @author wangfy
 * @Description TODO
 * @date 2019/3/26
 **/
public class BackTrackingOne {
    /**
     * @return java.util.List<java.util.List>
     * @date 2019/3/26 12:15
     * @Description 216. 组合总和 III(reveiw)
     * @Param [k, n]
     **/
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        backTrack(res, k, n, new ArrayList<Integer>(), 0, 1, Math.min(10, n));
        return res;
    }

    private void backTrack(List<List<Integer>> res, int k, int n, List<Integer> list, Integer sum, Integer begin, Integer end) {
        if (list.size() == k && sum == n) {
            res.add(list);
            return;
        }
        for (int i = begin; i < end; i++) {
            List<Integer> nList = new ArrayList<>(list);
            nList.add(i);
            backTrack(res, k, n, nList, sum + i, i + 1, end);
        }

    }

    class Trie {
        HashMap<Character, HashMap> lookup;
        Object end = new Object();

        public Trie() {
            this.lookup = new HashMap<>();
        }

        public void insert(String word) {
            HashMap<Character, HashMap> root = lookup;
            for (char c : word.toCharArray()) {
                if (root.get(c) == null) {
                    root.put(c, new HashMap<Character, HashMap>());

                }
                root = root.get(c);

            }
            root.put('#', null);
        }

    }
    /**
     * @date 2019/4/1 15:15
     * @return java.util.List<java.lang.String>
     * @Description 212. 单词搜索 II(review)
     * @Param [board, words]
     **/
    public List<String> findWords(char[][] board, String[] words) {
        int width = board.length;
        int height = board[0].length;
        Trie root = new Trie();
        Set<Character> seen = new HashSet<>();
        Set<String> res = new HashSet<>();
        for (String word : words) {
            root.insert(word);
            seen.add(word.charAt(0));
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (seen.contains(board[x][y])) {
                    dfs(res, x, y, width, height, board, root.lookup, new StringBuilder());
                }
            }
        }
        return new ArrayList<>(res);
    }

    public void dfs(Set<String> res, int x, int y, int width, int height, char[][] board, HashMap<Character, HashMap> node, StringBuilder word) {
        if (x >= 0 && x < width && y >= 0 && y < height && node.containsKey(board[x][y])) {
            node = node.get(board[x][y]);
            word.append(board[x][y]);
            if (node.containsKey('#')) {
                res.add(word.toString());
            }
            char t = board[x][y];
            board[x][y] = '0';
            dfs(res, x + 1, y, width, height, board, node, word);
            dfs(res, x - 1, y, width, height, board, node, word);
            dfs(res, x, y + 1, width, height, board, node, word);
            dfs(res, x, y - 1, width, height, board, node, word);
            word.deleteCharAt(word.length() - 1);
            board[x][y] = t;
        }
    }

    @Test
    public void test() {
//        List<List<Integer>> res = combinationSum3(3, 7);
//        res.forEach(o -> o.forEach(var -> System.out.println(var)));
        List<String> list = findWords(new char[][]{
                {'a', 'b'},
                {'c', 'd'}}, new String[]{"ab", "cb", "ad", "bd", "ac", "ca", "da", "bc", "db", "adcb", "dabc", "abb", "acb"});
        System.out.println(list);
    }
}
