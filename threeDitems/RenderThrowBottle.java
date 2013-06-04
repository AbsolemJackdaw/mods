///*    */ package threeDitems;
///*    */ 
///*    */ import bge;
///*    */ import bgz;
///*    */ import cpw.mods.fml.relauncher.Side;
///*    */ import cpw.mods.fml.relauncher.SideOnly;
///*    */ import mp;
///*    */ import net.minecraft.client.Minecraft;
///*    */ import org.lwjgl.opengl.GL11;
///*    */ import sq;
///*    */ import threeDitems.models.bottle;
///*    */ import wk;
///*    */ import wm;
///*    */ import ww;
///*    */ 
///*    */ @SideOnly(Side.CLIENT)
///*    */ public class RenderThrowBottle extends bgz
///*    */ {
///*    */   protected bottle bottle;
///*    */ 
///*    */   public RenderThrowBottle()
///*    */   {
///* 25 */     this.d = 0.5F;
///* 26 */     this.bottle = new bottle();
///*    */   }
///*    */ 
///*    */   public void renderBoat(mp ent, double par2, double par4, double par6, float par8, float par9)
///*    */   {
///* 34 */     GL11.glPushMatrix();
///*    */ 
///* 36 */     Minecraft.x().p.b("/subaraki/3d/bottle.png");
///* 37 */     GL11.glScalef(1.5F, 1.5F, 1.5F);
///* 38 */     GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
///* 39 */     GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
///* 40 */     GL11.glRotatef(-180.0F, 1.0F, 0.0F, 0.0F);
///* 41 */     GL11.glTranslatef(0.0F, 0.0F, 0.0F);
///* 42 */     this.bottle.a(ent, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
///*    */ 
///* 44 */     potionContent(ent);
///*    */ 
///* 46 */     GL11.glEnable(3042);
///* 47 */     GL11.glDisable(2896);
///* 48 */     GL11.glBlendFunc(1, 771);
///* 49 */     GL11.glColor4f(0.2F, 0.2F, 0.2F, 0.2F);
///* 50 */     this.bottle.renderBottle(ent, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
///*    */ 
///* 52 */     GL11.glPopMatrix();
///*    */   }
///*    */ 
///*    */   public void potionContent(mp p)
///*    */   {
///* 57 */     Minecraft mc = Minecraft.x();
///* 58 */     mc.p.b("/subaraki/3d/bottle.png");
///* 59 */     GL11.glEnable(3042);
///* 60 */     GL11.glDisable(2896);
///* 61 */     GL11.glBlendFunc(1, 771);
///* 62 */     if ((p instanceof sq))
///*    */     {
///* 64 */       sq player = (sq)p;
///* 65 */       if (player.cd() != null)
///*    */       {
///* 67 */         wm item = player.cd();
///* 68 */         if ((item.b().equals(wk.bt)) || (item.b().equals(wk.bu)))
///*    */         {
///* 70 */           int color = ((ww)item.b()).g(item.k());
///* 71 */           float red = (color >> 16 & 0xFF) / 255.0F;
///* 72 */           float green = (color >> 8 & 0xFF) / 255.0F;
///* 73 */           float blue = (color & 0xFF) / 255.0F;
///* 74 */           GL11.glColor4f(red, green, blue, 1.0F);
///* 75 */           this.bottle.renderContent(p, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
///*    */         }
///* 77 */         else if (player.cd().b().equals(wk.bE))
///*    */         {
///* 79 */           GL11.glColor4f(0.7F, 1.0F, 0.0F, 1.0F);
///* 80 */           this.bottle.renderContent(p, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
///*    */         }
///*    */       }
///*    */     }
///*    */   }
///*    */ 
///*    */   public void a(mp par1Entity, double par2, double par4, double par6, float par8, float par9)
///*    */   {
///* 94 */     renderBoat(par1Entity, par2, par4, par6, par8, par9);
///*    */   }
///*    */ }
//
///* Location:           C:\Users\Axel\Downloads\1.5.2 3DItems.zip
// * Qualified Name:     threeDitems.RenderThrowBottle
// * JD-Core Version:    0.6.2
// */