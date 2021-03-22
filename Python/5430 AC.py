import sys
from collections import deque
input = sys.stdin.readline

res = []
for i in range(int(input())): 
    cmd = list(input().rstrip())
    size = int(input())
    if(size == 0):
        arr = deque(input().rstrip()[1:-1])
    else:
        arr = deque(map(int, input().rstrip()[1:-1].split(',')))
    rev = False
    for j in cmd:
        if(j == 'R'):
            rev = not rev
        elif(j == 'D'):
            if(size == 0):
                size = -1
                ans = "error"
                break
            else:
                if(rev):
                    arr.pop()
                else:
                    arr.popleft()
                size -= 1
    if(size != -1):
        if(rev):
            arr.reverse()
        ans = "[" + ','.join(map(str, arr)) + "]"
    res.append(ans)

for i in res:
    print(i)