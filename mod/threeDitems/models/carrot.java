/*    */ package threeDitems.models;
/*    */
/*    */ import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
/*    */
/*    */
/*    */
/*    */ public class carrot extends ModelBase
/*    */ {
	/*    */   ModelRenderer Shape1;
	/*    */   ModelRenderer Shape2;
	/*    */   ModelRenderer Shape3;
	/*    */   ModelRenderer Shape4;
	/*    */   ModelRenderer Shape5;
	/*    */
	/*    */   public carrot()
	/*    */   {
		/* 18 */     this.textureHeight = 7;
		/* 19 */     this.textureWidth = 20;
		/*    */
		/* 21 */     this.Shape1 = new ModelRenderer(this, 0, 0);
		/* 22 */     this.Shape1.addBox(-0.5F, -2.0F, -0.5F, 1, 6, 1);
		/* 23 */     this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		/* 24 */     this.Shape1.setTextureSize(20, 7);
		/* 25 */     this.Shape1.mirror = true;
		/* 26 */     setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
		/* 27 */     this.Shape2 = new ModelRenderer(this, 0, 0);
		/* 28 */     this.Shape2.addBox(-1.0F, -1.5F, -1.0F, 2, 4, 2);
		/* 29 */     this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
		/* 30 */     this.Shape2.setTextureSize(20, 7);
		/* 31 */     this.Shape2.mirror = true;
		/* 32 */     setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
		/* 33 */     this.Shape3 = new ModelRenderer(this, 0, 0);
		/* 34 */     this.Shape3.addBox(-1.5F, -1.0F, -1.5F, 3, 2, 3);
		/* 35 */     this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
		/* 36 */     this.Shape3.setTextureSize(20, 7);
		/* 37 */     this.Shape3.mirror = true;
		/* 38 */     setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
		/* 39 */     this.Shape4 = new ModelRenderer(this, 9, 0);
		/* 40 */     this.Shape4.addBox(-2.0F, -4.0F, 0.0F, 4, 2, 0);
		/* 41 */     this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
		/* 42 */     this.Shape4.setTextureSize(20, 7);
		/* 43 */     this.Shape4.mirror = true;
		/* 44 */     setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
		/* 45 */     this.Shape5 = new ModelRenderer(this, 9, 0);
		/* 46 */     this.Shape5.addBox(-2.0F, -4.0F, 0.0F, 4, 2, 0);
		/* 47 */     this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
		/* 48 */     this.Shape5.setTextureSize(20, 7);
		/* 49 */     this.Shape5.mirror = true;
		/* 50 */     setRotation(this.Shape5, 0.0F, 1.570796F, 0.0F);
	/*    */   }
	/*    */
	/*    */   @Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	/*    */   {
		/* 55 */     super.render(entity, f, f1, f2, f3, f4, f5);
		/* 56 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		/* 57 */     this.Shape1.render(f5);
		/* 58 */     this.Shape2.render(f5);
		/* 59 */     this.Shape3.render(f5);
		/* 60 */     this.Shape4.render(f5);
		/* 61 */     this.Shape5.render(f5);
	/*    */   }
	/*    */
	/*    */   private void setRotation(ModelRenderer model, float x, float y, float z)
	/*    */   {
		/* 66 */     model.rotationPointX = x;
		/* 67 */     model.rotationPointY = y;
		/* 68 */     model.rotationPointZ = z;
	/*    */   }
/*    */ }

/* Location:           C:\Users\Axel\Downloads\1.5.2 3DItems.zip
 * Qualified Name:     threeDitems.models.carrot
 * JD-Core Version:    0.6.2
 */