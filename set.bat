@echo off
netsh interface ip set address "Ethernet" static 192.168.14.147 255.255.255.0 192.168.14.1 1
netsh interface ip add dns "Ethernet" 192.168.14.1
netsh interface ip add dns "Ethernet" 192.168.14.1 index=1
netsh interface ip add dns "Ethernet" 202.44.32.29 index=2
