package MyList;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        var list1 = new ArrayList<Integer>();
        list1.add(1);
        ret.add(list1);
        for (int i = 1; i < numRows; i++) {
            List<Integer> curRow = new ArrayList<>();
            curRow.add(1);
            
            var preRow = ret.get(i-1);
            for (int j = 1; j < i; j++) {
                curRow.add(preRow.get(j-1)+preRow.get(j));
            }
        }
    }
}