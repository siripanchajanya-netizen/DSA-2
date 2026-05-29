// CivicEye – Smart City Incident Monitoring & Response System
// CO1: BST and AVL Tree Implementation in Java

class IncidentNode {

    int incidentId;
    String incidentType;
    int height;

    IncidentNode left, right;

    IncidentNode(int incidentId, String incidentType) {
        this.incidentId = incidentId;
        this.incidentType = incidentType;
        this.height = 1;
    }
}

class AVLTree {

    // Get Height
    int getHeight(IncidentNode node) {

        if (node == null)
            return 0;

        return node.height;
    }

    // Get Maximum
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Right Rotation
    IncidentNode rightRotate(IncidentNode y) {

        IncidentNode x = y.left;
        IncidentNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // Left Rotation
    IncidentNode leftRotate(IncidentNode x) {

        IncidentNode y = x.right;
        IncidentNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    // Balance Factor
    int getBalance(IncidentNode node) {

        if (node == null)
            return 0;

        return getHeight(node.left) - getHeight(node.right);
    }

    // Insert Incident
    IncidentNode insert(IncidentNode node, int incidentId, String incidentType) {

        if (node == null)
            return new IncidentNode(incidentId, incidentType);

        if (incidentId < node.incidentId)
            node.left = insert(node.left, incidentId, incidentType);

        else if (incidentId > node.incidentId)
            node.right = insert(node.right, incidentId, incidentType);

        else
            return node;

        node.height = 1 + max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && incidentId < node.left.incidentId)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && incidentId > node.right.incidentId)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && incidentId > node.left.incidentId) {

            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && incidentId < node.right.incidentId) {

            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Search Incident
    IncidentNode search(IncidentNode root, int incidentId) {

        if (root == null || root.incidentId == incidentId)
            return root;

        if (incidentId < root.incidentId)
            return search(root.left, incidentId);

        return search(root.right, incidentId);
    }

    // Get Minimum Value Node
    IncidentNode minValueNode(IncidentNode node) {

        IncidentNode current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    // Delete Incident
    IncidentNode deleteNode(IncidentNode root, int incidentId) {

        if (root == null)
            return root;

        if (incidentId < root.incidentId)
            root.left = deleteNode(root.left, incidentId);

        else if (incidentId > root.incidentId)
            root.right = deleteNode(root.right, incidentId);

        else {

            if ((root.left == null) || (root.right == null)) {

                IncidentNode temp = null;

                if (temp == root.left)
                    temp = root.right;

                else
                    temp = root.left;

                if (temp == null) {

                    temp = root;
                    root = null;
                }

                else
                    root = temp;
            }

            else {

                IncidentNode temp = minValueNode(root.right);

                root.incidentId = temp.incidentId;
                root.incidentType = temp.incidentType;

                root.right = deleteNode(root.right, temp.incidentId);
            }
        }

        if (root == null)
            return root;

        root.height = max(getHeight(root.left), getHeight(root.right)) + 1;

        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {

            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {

            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Display Incident Records
    void inorder(IncidentNode root) {

        if (root != null) {

            inorder(root.left);

            System.out.println(
                "Incident ID: " + root.incidentId +
                " | Incident Type: " + root.incidentType
            );

            inorder(root.right);
        }
    }
}

// Main Class
public class CivicEyeAVL {

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        IncidentNode root = null;

        // Insert Incident Records
        root = tree.insert(root, 101, "Traffic Accident");
        root = tree.insert(root, 205, "Fire Alert");
        root = tree.insert(root, 150, "Water Leakage");
        root = tree.insert(root, 120, "Power Failure");
        root = tree.insert(root, 300, "Medical Emergency");

        System.out.println("=== CivicEye Incident Records ===");
        tree.inorder(root);

        // Search Incident
        IncidentNode found = tree.search(root, 150);

        if (found != null) {

            System.out.println("\nIncident Found:");
            System.out.println(
                "Incident ID: " + found.incidentId +
                " | Incident Type: " + found.incidentType
            );
        }

        else {

            System.out.println("\nIncident Not Found");
        }

        // Delete Incident
        root = tree.deleteNode(root, 205);

        System.out.println("\n=== Records After Deletion ===");
        tree.inorder(root);
    }
}