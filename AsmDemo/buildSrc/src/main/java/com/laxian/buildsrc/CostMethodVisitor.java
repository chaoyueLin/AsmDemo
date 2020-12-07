package com.laxian.buildsrc;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class CostMethodVisitor extends AdviceAdapter {

    private static final String TAG = "AsmMethodVisitor -> ";

    protected CostMethodVisitor(int api, MethodVisitor mv, int access, String name, String desc) {
        super(api, mv, access, name, desc);
        System.out.println(TAG + "CostMethodVisitor -> " + name + ": " + desc);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        System.out.println(TAG + "visitCode" + this.methodDesc + this.firstLocal + this.methodAccess + this.methodAccess + this.nextLocal + this.mv.toString());
    }

    @Override
    public void visitLabel(Label label) {
        super.visitLabel(label);
        System.out.println(TAG + "visitLabel" + label.info);
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
        System.out.println(TAG + "visitInsn");
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        super.visitVarInsn(opcode, var);
        System.out.println(TAG + "visitVarInsn" + opcode + ": " + var);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        super.visitFieldInsn(opcode, owner, name, desc);
        System.out.println(TAG + "visitFieldInsn");
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        super.visitIntInsn(opcode, operand);
        System.out.println(TAG + "visitIntInsn");
    }

    @Override
    public void visitLdcInsn(Object cst) {
        super.visitLdcInsn(cst);
        System.out.println(TAG + "visitLdcInsn");
    }

    @Override
    public void visitMultiANewArrayInsn(String desc, int dims) {
        super.visitMultiANewArrayInsn(desc, dims);
        System.out.println(TAG + "visitMultiANewArrayInsn");
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        super.visitTypeInsn(opcode, type);
        System.out.println(TAG + "visitTypeInsn");
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc) {
        super.visitMethodInsn(opcode, owner, name, desc);
        System.out.println(TAG + "visitMethodInsn");
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
        System.out.println(TAG + "visitMethodInsn: " + opcode + ": " + owner + ": " + name + ": " + desc + ": " + itf);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
        System.out.println(TAG + "visitInvokeDynamicInsn");
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        super.visitJumpInsn(opcode, label);
        System.out.println(TAG + "visitJumpInsn: " + opcode + ": " + label.info);
    }

    @Override
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        super.visitLookupSwitchInsn(dflt, keys, labels);
        System.out.println(TAG + "visitLookupSwitchInsn");
    }

    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        super.visitTableSwitchInsn(min, max, dflt, labels);
        System.out.println(TAG + "visitTableSwitchInsn");
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        super.visitTryCatchBlock(start, end, handler, type);
        System.out.println(TAG + "visitTryCatchBlock");
    }

    private static final String ANNOTATION_SIGNATURE = "Lcom/example/asmdemo/base/ACost;";

    private boolean isInsert;
    private String methodName;

    @Override
    protected void onMethodEnter() {
        if (isInsert) {
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(21, l0);
            mv.visitLdcInsn(methodName);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitMethodInsn(INVOKESTATIC, "com/example/asmdemo/base/CostTimeUtil", "setStartTime", "(Ljava/lang/String;J)V", false);
        }
        super.onMethodEnter();
        System.out.println(TAG + "onMethodEnter");
    }

    @Override
    protected void onMethodExit(int opcode) {
        if (isInsert) {
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(23, l2);
            mv.visitLdcInsn(methodName);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitMethodInsn(INVOKESTATIC, "com/example/asmdemo/base/CostTimeUtil", "setEndTime", "(Ljava/lang/String;J)V", false);
        }
        super.onMethodExit(opcode);
        System.out.println(TAG + "onMethodExit" + opcode);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (ANNOTATION_SIGNATURE.equals(desc)) {
            isInsert = true;
            System.out.println(TAG + "visitAnnotation -> hit: " + desc + visible);
            return new AnnotationVisitor(ASM5) {
                @Override
                public void visit(String name, Object value) {
                    CostMethodVisitor.this.methodName = (String) value;
                    super.visit(name, value);
                    System.out.println(TAG + "AnnotationVisitor.visit: " + name + ": " + value);
                }
            };
        }
        System.out.println(TAG + "visitAnnotation" + desc + visible);
        return super.visitAnnotation(desc, visible);
    }
}
