import sys
sys.setrecursionlimit(150000)
input = sys.stdin.readline

N = int(input())
ans = 0
edges = []
tree = [i for i in range(N + 1)]
count = [1 for _ in range(N + 1)]
for i in range(N - 1):
    edges.append(list(map(int, input().split())))

edges.sort(reverse = True, key = lambda x : x[2])

def find(x):
    if(tree[x] == x): return x
    tree[x] = find(tree[x])
    return tree[x]

def union(x, y, w):
    global ans
    x = find(x)
    y = find(y)
    
    if(count[x] > count[y]):
        x, y = y, x
    ans += count[x] * count[y] * w
    tree[x] = tree[y]
    count[y] += count[x]

for edge in edges:
    x, y, w = edge
    union(x, y, w)

print(ans)