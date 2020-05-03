	.data
	.text
main:
	jal L10
	li $v0, 10
	syscall
L0:
	move $t0, $fp
	move $t1, $ra
	move $fp, $sp
	addi $sp, $sp, -36
	sw $t0, 8($sp)
	sw $t1, 4($sp)
	sw $a0, 0($fp)
	sw $a1, -4($fp)
	sw $a2, -8($fp)
	sw $a3, -12($fp)
	lw $t0, -4($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, -16($fp)
L12:
	lw $t0, 0($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a0, 4($sp)
	add $sp, 4
	jal entryLength
	sw $v0, -20($fp)
	lw $t0, -16($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, -20($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, 4($sp)
	add $sp, 4
	slt $t0, $t1, $t2
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	beq $t0, $zero, L14
	j L13
L13:
	lw $t0, 0($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, -24($fp)
	lw $t0, -12($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, -16($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -24($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	lw $t0, -16($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, -8($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, 4($sp)
	add $sp, 4
	addu $t0, $t1, $t2
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, -16($fp)
	j L12
L14:
L1:
	move $sp, $fp
	lw $ra, -32($fp)
	lw $fp, -28($fp)
	j $ra
L2:
	move $t0, $fp
	move $t1, $ra
	move $fp, $sp
	addi $sp, $sp, -20
	sw $t0, 8($sp)
	sw $t1, 4($sp)
	sw $a0, 0($fp)
L15:
	lw $t0, 0($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a0, 4($sp)
	add $sp, 4
	jal entryPrintString
	lw $t0, -4($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a0, 4($sp)
	add $sp, 4
	jal entryReadInt
	sw $v0, -4($fp)
	lw $t0, -4($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 0
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, 4($sp)
	add $sp, 4
	slt $t0, $t1, $t2
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	beq $t0, $zero, L17
	j L16
L16:
	j L15
L17:
	lw $t0, -4($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, -8($fp)
	j L3
L3:
	lw $v0, -8($fp)
	move $sp, $fp
	lw $ra, -16($fp)
	lw $fp, -12($fp)
	j $ra
L4:
	move $t0, $fp
	move $t1, $ra
	move $fp, $sp
	addi $sp, $sp, -32
	sw $t0, 8($sp)
	sw $t1, 4($sp)
	sw $a0, 0($fp)
	lw $t0, 0($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, -4($fp)
	li $t0, 0
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 0
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	lw $t0, 0($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, -8($fp)
	li $t0, 0
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 1
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -8($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 2
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, -12($fp)
L18:
	lw $t0, 0($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a0, 4($sp)
	add $sp, 4
	jal entryLength
	sw $v0, -16($fp)
	lw $t0, -12($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, -16($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, 4($sp)
	add $sp, 4
	slt $t0, $t1, $t2
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	beq $t0, $zero, L20
	j L19
L19:
	lw $t0, 0($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, -20($fp)
	lw $t0, -20($fp)
mul $t1, $t1, 1
addi $t1, $t1, 4
add $t1, $t1, $t0
	lb $t0, ($t1)
	lw $t0, 4($sp)
	add $sp, 4
	beq $t0, $zero, L22
	j L21
L21:
	lw $t0, 0($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 2
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, -12($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, 4($sp)
	add $sp, 4
	mul $t0, $t1, $t2
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, -12($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 0
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a3, 4($sp)
	add $sp, 4
	lw $a2, 4($sp)
	add $sp, 4
	lw $a1, 4($sp)
	add $sp, 4
	lw $a0, 4($sp)
	add $sp, 4
	jal L0
L22:
	lw $t0, -12($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 1
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, 4($sp)
	add $sp, 4
	addu $t0, $t1, $t2
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, -12($fp)
	j L18
L20:
L5:
	move $sp, $fp
	lw $ra, -28($fp)
	lw $fp, -24($fp)
	j $ra
L6:
	move $t0, $fp
	move $t1, $ra
	move $fp, $sp
	addi $sp, $sp, -12
	sw $t0, 8($sp)
	sw $t1, 4($sp)
	li $t0, 1
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 2
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a1, 4($sp)
	add $sp, 4
	lw $a0, 4($sp)
	add $sp, 4
	jal entryNew
	sw $v0, 0($fp)
	li $t0, 10
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 0
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, 0($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 0
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 1
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, 0($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	lw $t0, 0($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a0, 4($sp)
	add $sp, 4
	jal entryPrintString
L7:
	move $sp, $fp
	lw $ra, -8($fp)
	lw $fp, -4($fp)
	j $ra
L8:
	move $t0, $fp
	move $t1, $ra
	move $fp, $sp
	addi $sp, $sp, -24
	sw $t0, 8($sp)
	sw $t1, 4($sp)
	sw $a0, 0($fp)
	li $t0, 0
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, -4($fp)
L24:
	lw $t0, 0($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a0, 4($sp)
	add $sp, 4
	jal entryLength
	sw $v0, -8($fp)
	lw $t0, -4($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, -8($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, 4($sp)
	add $sp, 4
	slt $t0, $t1, $t2
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	beq $t0, $zero, L26
	j L25
L25:
	lw $t0, 0($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, -12($fp)
	lw $t0, -12($fp)
mul $t1, $t1, 1
addi $t1, $t1, 4
add $t1, $t1, $t0
	lb $t0, ($t1)
	lw $t0, 4($sp)
	add $sp, 4
	beq $t0, $zero, L28
	j L27
L27:
	lw $t0, -4($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a0, 4($sp)
	add $sp, 4
	jal entryPrintInt
	jal L6
L28:
	j L24
L26:
L9:
	move $sp, $fp
	lw $ra, -20($fp)
	lw $fp, -16($fp)
	j $ra
L10:
	move $t0, $fp
	move $t1, $ra
	move $fp, $sp
	addi $sp, $sp, -28
	sw $t0, 8($sp)
	sw $t1, 4($sp)
	li $t0, 1
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 27
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a1, 4($sp)
	add $sp, 4
	lw $a0, 4($sp)
	add $sp, 4
	jal entryNew
	sw $v0, -4($fp)
	li $t0, 69
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 0
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 110
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 1
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 116
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 2
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 101
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 3
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 114
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 4
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 32
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 5
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 97
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 6
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 32
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 7
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 112
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 8
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 111
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 9
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 115
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 10
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 105
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 11
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 116
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 12
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 105
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 13
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 118
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 14
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 101
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 15
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 32
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 16
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 105
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 17
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 110
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 18
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 116
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 19
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 101
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 20
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 103
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 21
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 101
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 22
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 114
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 23
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 58
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 24
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 32
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 25
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	li $t0, 0
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 26
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t2, 4($sp)
	add $sp, 4
	lw $t1, -4($fp)
	li $t3, 1
	mul $t2, $t2, $t3
	add $t1, $t1, $t2
	li $t3, 4
	add $t1, $t1, $t3
	lw $t2, 4($sp)
	add $sp, 4
	sb $t2, ($t1)
	lw $t0, -4($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a0, 4($sp)
	add $sp, 4
	jal L2
	sw $v0, -8($fp)
	lw $t0, -8($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, 0($fp)
	li $t0, 1
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 0($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a1, 4($sp)
	add $sp, 4
	lw $a0, 4($sp)
	add $sp, 4
	jal entryNew
	sw $v0, -16($fp)
	lw $t0, -16($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $t0, 4($sp)
	add $sp, 4
	sw $t0, -12($fp)
	lw $t0, -12($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 0
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 1
	sub $sp, 4
	sw $t0, 4($sp)
	li $t0, 1
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a3, 4($sp)
	add $sp, 4
	lw $a2, 4($sp)
	add $sp, 4
	lw $a1, 4($sp)
	add $sp, 4
	lw $a0, 4($sp)
	add $sp, 4
	jal L0
	lw $t0, -12($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a0, 4($sp)
	add $sp, 4
	jal L4
	lw $t0, -12($fp)
	sub $sp, 4
	sw $t0, 4($sp)
	lw $a0, 4($sp)
	add $sp, 4
	jal L8
L11:
	move $sp, $fp
	lw $ra, -24($fp)
	lw $fp, -20($fp)
	j $ra

entryPrintInt:
	li $v0, 1
	syscall
exitPrintInt:	
	j $ra

entryPrintChar:
	li $v0, 11
	syscall
exitPrintChar:
	j $ra

entryPrintBool:
	bne $a0, 0, printBoolTrue
	j printBoolFalse
printBoolTrue:
	li $a0, 84
	li $v0, 11
	syscall
	j exitPrintBool
printBoolFalse:
	li $a0, 70
	li $v0, 11
	syscall
exitPrintBool:
	j $ra

entryPrintString:
	li $t0, 4
	add $a0, $a0, $t0
	li $v0, 4
	syscall
exitPrintString:
	j $ra

entryLength:
	lw $v0, ($a0)
exitLength:
	j $ra

entryNew:
	mul $t0, $a0, $a1
	li $t1, 4
	add $t0, $t0, $t1
	move $a0, $t0
	li $v0, 9
	syscall
	sw $a1, ($v0)
exitNew:
	j $ra

entryReadChar:
	la $a0, buffer
	li $a1, 3
	li $v0, 8
	syscall
	lb $v0, buffer
exitReadChar:
	j $ra

entryReadInt:
	li $v0, 5
	syscall
exitReadInt:
	j $ra

entryReadBool:
	la $a0, buffer
	li $a1, 3
	li $v0, 8
	syscall
	lb $v0, buffer
	li $t1, 84
	seq $v0, $v0, $t1
exitReadBool:
	j $ra

entryReadString:
	addi $a0, $a0, 4
	li $v0, 8
	syscall
exitReadString:
	j $ra
