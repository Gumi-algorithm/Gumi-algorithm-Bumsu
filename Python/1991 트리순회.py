n = int(input())
tree = {}
for i in range(n):
    data = input().split()
    tree[data[0]] = [data[1], data[2]]

def preorder(node):
    if node == '.':
        return
    print(node, end = "")
    preorder(tree[node][0])
    preorder(tree[node][1])

def inorder(node):
    if node == '.':
        return
    inorder(tree[node][0])
    print(node, end = "")
    inorder(tree[node][1])

def postorder(node):
    if node == '.':
        return
    postorder(tree[node][0])
    postorder(tree[node][1])
    print(node, end = "")

preorder("A")
print()
inorder("A")
print()
postorder("A")