.global _start
.data
buf: .skip 1024
.text
_start:
	movq $0, %rdi # stdin file descriptor
	movq $buf, %rsi # buffer
	movq $1024, %rdx # buffer length
	movq $0, %rax # sys_read
	syscall
	movq $1, %rdi # stdout file descriptor
	movq $buf, %rsi # message to print
	movq %rax, %rdx # message length
	movq $1, %rax # sys_write
	syscall
	movq $0, %rdi
	movq $60, %rax
	syscall
