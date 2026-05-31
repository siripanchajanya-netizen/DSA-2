// CivicEye – Smart City Incident Monitoring & Response System
// CO2: Segment Tree and Fenwick Tree Implementation

class SegmentTree {

    int[] tree;
    int n;

    // Constructor
    SegmentTree(int[] arr) {

        n = arr.length;

        tree = new int[4 * n];

        build(arr, 1, 0, n - 1);
    }

    // Build Segment Tree
    void build(int[] arr, int node, int start, int end) {

        if (start == end) {

            tree[node] = arr[start];
        }

        else {

            int mid = (start + end) / 2;

            build(arr, 2 * node, start, mid);

            build(arr, 2 * node + 1, mid + 1, end);

            tree[node] = tree[2 * node]
                    + tree[2 * node + 1];
        }
    }

    // Range Query
    int query(int node, int start,
              int end, int left, int right) {

        // No overlap
        if (right < start || end < left)
            return 0;

        // Complete overlap
        if (left <= start && end <= right)
            return tree[node];

        // Partial overlap
        int mid = (start + end) / 2;

        int p1 = query(
                2 * node,
                start,
                mid,
                left,
                right
        );

        int p2 = query(
                2 * node + 1,
                mid + 1,
                end,
                left,
                right
        );

        return p1 + p2;
    }
}

// --------------------------------------------------
// FENWICK TREE
// --------------------------------------------------

class FenwickTree {

    int[] bit;
    int size;

    // Constructor
    FenwickTree(int n) {

        size = n;

        bit = new int[n + 1];
    }

    // Update Function
    void update(int index, int value) {

        while (index <= size) {

            bit[index] += value;

            index += index & (-index);
        }
    }

    // Prefix Sum Query
    int query(int index) {

        int sum = 0;

        while (index > 0) {

            sum += bit[index];

            index -= index & (-index);
        }

        return sum;
    }
}

// --------------------------------------------------
// MAIN CLASS
// --------------------------------------------------

public class CivicEyeCO2 {

    public static void main(String[] args) {

        // Smart City Incident Data
        int[] incidents = {
                12,
                8,
                15,
                10,
                20,
                18,
                25
        };

        // --------------------------------------------------
        // SEGMENT TREE
        // --------------------------------------------------

        SegmentTree st = new SegmentTree(incidents);

        System.out.println("=== SEGMENT TREE ===");

        int result = st.query(
                1,
                0,
                incidents.length - 1,
                1,
                4
        );

        System.out.println(
                "Total incidents between Zone 1 and Zone 4: "
                        + result
        );

        // --------------------------------------------------
        // FENWICK TREE
        // --------------------------------------------------

        FenwickTree ft = new FenwickTree(
                incidents.length
        );

        for (int i = 0; i < incidents.length; i++) {

            ft.update(i + 1, incidents[i]);
        }

        System.out.println("\n=== FENWICK TREE ===");

        int prefix = ft.query(5);

        System.out.println(
                "Total incidents till Zone 5: "
                        + prefix
        );

        // --------------------------------------------------
        // FINAL ANALYSIS
        // --------------------------------------------------

        System.out.println("\n=== CIVICEYE ANALYSIS ===");

        System.out.println(
                "Segment Tree handled range queries efficiently."
        );

        System.out.println(
                "Fenwick Tree calculated prefix sums efficiently."
        );

        System.out.println(
                "The system optimized smart city incident analytics."
        );
    }
}