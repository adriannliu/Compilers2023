# Prompts the user to enter 2 numbers and prints their product.
# @author Adrian Liu
# @version 5/14/24

.data
	msg1: .asciiz "Input a number to multiply: "
	msg2: .asciiz "Input another number to multiply: "
	msg3: .asciiz "The product is: "
.text 0x00400000
.globl main

main:
	#asks for first number, store in $t0
	li $v0 4
	la $a0 msg2
	syscall
	li $v0 5
	syscall
	move $t0 $v0
	
	#asks for second number to multiply
	li $v0 4
	la $a0 msg2
	syscall
	li $v0 5
	syscall
	move $t1 $v0
	
	#outputs the introduction for the product
	li $v0 4
	la $a0 msg3
	syscall
	
	#multiplies the numbers and then outputs the product
	mult $t0 $t1
	mflo $a0
	li $v0 1
	syscall
	
	li $v0 10
	syscall
