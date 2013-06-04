/*     */ package threeDitems.models;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class saddle extends ModelBase
/*     */ {
/*     */   ModelRenderer Shape1;
/*     */   ModelRenderer Shape2;
/*     */   ModelRenderer Shape3;
/*     */   ModelRenderer Shape4;
/*     */   ModelRenderer Shape5;
/*     */   ModelRenderer Shape6;
/*     */   ModelRenderer Shape7;
/*     */   ModelRenderer Shape8;
/*     */   ModelRenderer Shape9;
/*     */   ModelRenderer Shape10;
/*     */   ModelRenderer Shape11;
/*     */ 
/*     */   public saddle()
/*     */   {
/*  24 */     this.textureWidth = 24;
/*  25 */     this.textureHeight = 9;
/*     */ 
/*  27 */     this.Shape1 = new ModelRenderer(this, 0, 0);
/*  28 */     this.Shape1.addBox(-2.0F, -1.0F, -4.0F, 4, 1, 8);
/*  29 */     this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  30 */     this.Shape1.setTextureSize(24, 9);
/*  31 */     this.Shape1.mirror = true;
/*  32 */     setRotation(this.Shape1, 3.141593F, 0.0F, 0.0F);
/*  33 */     this.Shape2 = new ModelRenderer(this, 0, 0);
/*  34 */     this.Shape2.addBox(-2.0F, -1.0F, -5.0F, 4, 1, 1);
/*  35 */     this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  36 */     this.Shape2.setTextureSize(24, 9);
/*  37 */     this.Shape2.mirror = true;
/*  38 */     setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
/*  39 */     this.Shape3 = new ModelRenderer(this, 0, 0);
/*  40 */     this.Shape3.addBox(-2.0F, -1.0F, 4.0F, 4, 1, 1);
/*  41 */     this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  42 */     this.Shape3.setTextureSize(24, 9);
/*  43 */     this.Shape3.mirror = true;
/*  44 */     setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
/*  45 */     this.Shape4 = new ModelRenderer(this, 0, 0);
/*  46 */     this.Shape4.addBox(2.0F, -0.5F, -4.0F, 1, 1, 1);
/*  47 */     this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  48 */     this.Shape4.setTextureSize(24, 9);
/*  49 */     this.Shape4.mirror = true;
/*  50 */     setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
/*  51 */     this.Shape5 = new ModelRenderer(this, 0, 0);
/*  52 */     this.Shape5.addBox(2.0F, 0.5F, -3.0F, 1, 1, 6);
/*  53 */     this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  54 */     this.Shape5.setTextureSize(24, 9);
/*  55 */     this.Shape5.mirror = true;
/*  56 */     setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
/*  57 */     this.Shape6 = new ModelRenderer(this, 0, 0);
/*  58 */     this.Shape6.addBox(2.0F, -0.5F, 3.0F, 1, 1, 1);
/*  59 */     this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  60 */     this.Shape6.setTextureSize(24, 9);
/*  61 */     this.Shape6.mirror = true;
/*  62 */     setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
/*  63 */     this.Shape7 = new ModelRenderer(this, 0, 0);
/*  64 */     this.Shape7.addBox(-3.0F, -0.5F, 3.0F, 1, 1, 1);
/*  65 */     this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  66 */     this.Shape7.setTextureSize(24, 9);
/*  67 */     this.Shape7.mirror = true;
/*  68 */     setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
/*  69 */     this.Shape8 = new ModelRenderer(this, 0, 0);
/*  70 */     this.Shape8.addBox(-3.0F, -0.5F, -4.0F, 1, 1, 1);
/*  71 */     this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  72 */     this.Shape8.setTextureSize(24, 9);
/*  73 */     this.Shape8.mirror = true;
/*  74 */     setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
/*  75 */     this.Shape9 = new ModelRenderer(this, 0, 0);
/*  76 */     this.Shape9.addBox(-3.0F, 0.5F, -3.0F, 1, 1, 6);
/*  77 */     this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  78 */     this.Shape9.setTextureSize(24, 9);
/*  79 */     this.Shape9.mirror = true;
/*  80 */     setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
/*  81 */     this.Shape10 = new ModelRenderer(this, 20, 0);
/*  82 */     this.Shape10.addBox(1.0F, 1.0F, -0.5F, 1, 3, 1);
/*  83 */     this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  84 */     this.Shape10.setTextureSize(24, 9);
/*  85 */     this.Shape10.mirror = true;
/*  86 */     setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
/*  87 */     this.Shape11 = new ModelRenderer(this, 20, 0);
/*  88 */     this.Shape11.addBox(-2.0F, 1.0F, -0.5F, 1, 3, 1);
/*  89 */     this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  90 */     this.Shape11.setTextureSize(24, 9);
/*  91 */     this.Shape11.mirror = true;
/*  92 */     setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  97 */     super.render(entity, f, f1, f2, f3, f4, f5);
/*  98 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/*  99 */     this.Shape1.render(f5);
/* 100 */     this.Shape2.render(f5);
/* 101 */     this.Shape3.render(f5);
/* 102 */     this.Shape4.render(f5);
/* 103 */     this.Shape5.render(f5);
/* 104 */     this.Shape6.render(f5);
/* 105 */     this.Shape7.render(f5);
/* 106 */     this.Shape8.render(f5);
/* 107 */     this.Shape9.render(f5);
/* 108 */     this.Shape10.render(f5);
/* 109 */     this.Shape11.render(f5);
/*     */   }
/*     */ 
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 114 */     model.rotationPointX = x;
/* 115 */     model.rotationPointY = y;
/* 116 */     model.rotationPointZ = z;
/*     */   }
/*     */ }

/* Location:           C:\Users\Axel\Downloads\1.5.2 3DItems.zip
 * Qualified Name:     threeDitems.models.saddle
 * JD-Core Version:    0.6.2
 */