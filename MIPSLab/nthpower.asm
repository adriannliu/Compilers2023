# Prompts the user to enter 2 numbers. Prints the first number taken to the power of
# the second number. Assumes valid input of positive integers
# @author Adrian Liu
# @version 5/14/24

.data
	msg:	.asciiz "\nEnter the base number: "
	msg2: 	.asciiz "Enter the power: "
	msg3:	.asciiz " to the power of "
	msg4:	.asciiz " is: "
.text
.globl main
main: 
	# ask for base number
	li $v0 4
	la $a0 msg
	syscall
	
	# store base number in register $t0
	li $v0 5
	syscall
	move $t0 $v0
	
	# ask for power number
	li $v0 4
	la $a0 msg2
	syscall
	
	# store power in register $t1
	li $v0 5
	syscall
	move $t1 $v0
	
	# set $t3 equal to $t0 for multiplication purposes
	move $t3 $t0
	# set $t3 equal to $t1 for multiplication purposes
	move $t4 $t1
	
	j eval

# evaluate $t0 to the power of $t1
eval:
	beq $t4 1 result
	mult $t3 $t0
	mflo $t3
	subu $t4 $t4 1
	j eval
	
# print the result
result:
	li $v0 1
	move $a0 $t0
	syscall
	
	li $v0 4
	la $a0 msg3
	syscall
	
	li $v0 1
	move $a0 $t1
	syscall
	
	li $v0 4
	la $a0 msg4
	syscall
	
	li $v0 1
	move $a0 $t3
	syscall
	
	# normal termination
	li $v0 10
	syscall

	