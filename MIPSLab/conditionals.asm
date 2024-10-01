# Prompts the user to enter a number and prints if the number is even or odd
# @author Adrian Liu
# @version 5/14/24
.data
	prompt: .asciiz "Input a number: "
	evenmsg: .asciiz "Your number is even."
	oddmsg: .asciiz "Your number is odd."
.text 0x00400000
.globl main
main:
	#prompts user to enter a number
	li $v0 4
	la $a0 prompt
	syscall
	li $v0 5
	syscall
	move $t0 $v0
	
	#checks by dividing by 2
	li $t1 2
	divu $t0 $t1
	mfhi $t2
	
	#prints even message if remainder is 0; else, prints odd message
	li $v0 4
	beq $t2 0 even
	la $a0 oddmsg
	syscall
	
	j end

even:
	la $a0 evenmsg
	syscall
	j end
	
end:
	li $v0 10
	syscall

