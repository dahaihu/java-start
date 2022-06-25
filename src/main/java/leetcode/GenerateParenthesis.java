package leetcode;

import java.util.List;
import java.util.ArrayList;

public class GenerateParenthesis {
    List<String> result;

    public GenerateParenthesis() {
        this.result = new ArrayList<>();
    }

    public void recur(String cur, int c1, int c2) {
        if (c1 == 0 && c2 == 0) {
            this.result.add(cur);
            return;
        }
        if (c1 == c2) {
            this.recur(cur + "(", c1 - 1, c2);
        } else {
            //c1 < c2
            this.recur(cur + ")", c1, c2 - 1);
            if (c1 > 0) {
                this.recur(cur + "(", c1 - 1, c2);
            }
        }
    }

    public List<String> generateParenthesis(int n) {
        this.recur("", n, n);
        return this.result;
    }
}
