# import sys
# from collections import deque
# input = sys.stdin.readline

# N, K = map(int, input().split())
# que = deque([i for i in range(1, N+1)])
# result = []
# while(que):
#     for i in range(K - 1):
#         que.append(que.popleft())
#     result.append(que.popleft())
# print("<"+", ".join(str(x) for x in result)+">")