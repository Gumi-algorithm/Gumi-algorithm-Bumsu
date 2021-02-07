from collections import deque

T = int(input())
result = [""] * T

for i in range(T):
    password = input()
    left, right = deque(), deque()
    for j in password:
        if j == '<':
            if len(left) != 0:
                right.appendleft(left.pop())
        elif j == '>':
            if len(right) != 0:
                left.append(right.popleft())
        elif j == '-':
            if len(left) != 0:
                left.pop()
        else:
            left.append(j)

    result[i] = ''.join(left) + ''.join(right)
    
for i in result:
    print(i)