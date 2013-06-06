package gravestone.grave;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGrave extends ModelBase
{
	//fields
	public ModelRenderer Shape1;
	public ModelRenderer Shape2;
	public ModelRenderer Shape3;
	public ModelRenderer Shape4;
	public ModelRenderer Shape5;
	public ModelRenderer Shape6;
	public ModelRenderer Shape7;
	public ModelRenderer Shape8;
	public ModelRenderer Shape9;
	public ModelRenderer Shape10;
	public ModelRenderer Shape11;
	public ModelRenderer Shape12;
	public ModelRenderer Shape13;
	public ModelRenderer Shape14;
	public ModelRenderer Shape15;
	public ModelRenderer Shape16;
	public ModelRenderer Shape17;
	public ModelRenderer Base1;
	public ModelRenderer Base2;
	public ModelRenderer Base3;

	ModelRenderer Base4;
	ModelRenderer Base5;
	ModelRenderer Base6;
	ModelRenderer Base7;
	ModelRenderer Base8;
	ModelRenderer Pillar1;
	ModelRenderer Base9;
	ModelRenderer Base10;
	ModelRenderer Pillar2;
	ModelRenderer Pillar3;
	ModelRenderer Pillar4;
	ModelRenderer Pillar5;
	ModelRenderer Pillar6;
	ModelRenderer Shape18;
	ModelRenderer Shape19;
	ModelRenderer Shape20;
	ModelRenderer Shape21;
	ModelRenderer Shape22;
	ModelRenderer Shape23;

	ModelRenderer Cross1;
	ModelRenderer Cross2;
	ModelRenderer Rope1;
	ModelRenderer Rope2;
	ModelRenderer Rope3;
	ModelRenderer Rope4;
	ModelRenderer ground1;
	ModelRenderer Ground2;
	ModelRenderer ground3;
	ModelRenderer ground4;
	ModelRenderer ground5;
	ModelRenderer ground6;
	ModelRenderer ground7;
	ModelRenderer ground8;

	ModelRenderer b;
	ModelRenderer b1;
	ModelRenderer b2;
	ModelRenderer b3;
	ModelRenderer b4;
	ModelRenderer b5;
	ModelRenderer b8;
	ModelRenderer b9;
	ModelRenderer b10;
	ModelRenderer b11;
	ModelRenderer b12;
	ModelRenderer b13;
	ModelRenderer b14;
	ModelRenderer b15;
	ModelRenderer b16;
	ModelRenderer b17;
	ModelRenderer b18;
	ModelRenderer b19;
	ModelRenderer b20;
	ModelRenderer b21;
	ModelRenderer b22;
	ModelRenderer b23;
	ModelRenderer b24;
	ModelRenderer b25;
	ModelRenderer b252;
	ModelRenderer b26;
	ModelRenderer b27;
	ModelRenderer b28;
	ModelRenderer b29;
	ModelRenderer b30;
	ModelRenderer b7;

	ModelRenderer knight1;
	ModelRenderer knight2;
	ModelRenderer knight3;
	ModelRenderer knight4;
	ModelRenderer knight5;
	ModelRenderer knight6;
	ModelRenderer knight7;
	ModelRenderer knight8;
	ModelRenderer knight9;
	ModelRenderer knight10;
	ModelRenderer knight11;
	ModelRenderer knight12;
	ModelRenderer knight13;
	ModelRenderer knight14;
	ModelRenderer knight15;
	ModelRenderer knight17;
	ModelRenderer knight16;

	public ModelGrave()
	{

		textureWidth = 64;
		textureHeight = 64;
		///CrossShaped Model
		Shape1 = new ModelRenderer(this, 0, 0+32);
		Shape1.addBox(-2F, 0F, -2F, 4, 4, 4);
		Shape1.setRotationPoint(0F, 19F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 8+32);
		Shape2.addBox(-3F, 0F, -3F, 6, 1, 6);
		Shape2.setRotationPoint(0F, 23F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 25, 0+32);
		Shape3.addBox(-1F, 0F, -1F, 2, 14, 2);
		Shape3.setRotationPoint(0F, 5F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 33, 0+32);
		Shape4.addBox(-0.5F, -5F, -2F, 1, 10, 2);
		Shape4.setRotationPoint(0F, 10F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, -1.570796F, 0F, 0F);

		/**
		 * Prefix's Grave Model (simple)
		 * */
		Base1 = new ModelRenderer(this, 0, 55);
		Base1.addBox(-8F, 0F, -4F, 16, 1, 8);
		Base1.setRotationPoint(0F, 23F, 0F);
		Base1.setTextureSize(64, 64);
		Base1.mirror = true;
		setRotation(Base1, 0F, 0F, 0F);
		Base2 = new ModelRenderer(this, 0, 47);
		Base2.addBox(-7.5F, 0F, -3.5F, 15, 1, 7);
		Base2.setRotationPoint(0F, 22F, 0F);
		Base2.setTextureSize(64, 64);
		Base2.mirror = true;
		setRotation(Base2, 0F, 0F, 0F);
		Base3 = new ModelRenderer(this, 0, 40);
		Base3.addBox(-7F, 0F, -3F, 14, 1, 6);
		Base3.setRotationPoint(0F, 21F, 0F);
		Base3.setTextureSize(64, 64);
		Base3.mirror = true;
		setRotation(Base3, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 0, 24);
		Shape14.addBox(-6F, 0F, -1F, 12, 14, 2);
		Shape14.setRotationPoint(0F, 7F, 0F);
		Shape14.setTextureSize(64, 64);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(this, 0, 21);
		Shape15.addBox(-5.5F, 0F, 0F, 1, 1, 2);
		Shape15.setRotationPoint(0F, 6.5F, -1F);
		Shape15.setTextureSize(64, 64);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
		Shape16 = new ModelRenderer(this, 0, 18);
		Shape16.addBox(-5F, -2F, 0F, 10, 1, 2);
		Shape16.setRotationPoint(0F, 8F, -1F);
		Shape16.setTextureSize(64, 64);
		Shape16.mirror = true;
		setRotation(Shape16, 0F, 0F, 0F);
		Shape17 = new ModelRenderer(this, 0, 21);
		Shape17.addBox(4.5F, 0F, 0F, 1, 1, 2);
		Shape17.setRotationPoint(0F, 6.5F, -1F);
		Shape17.setTextureSize(64, 64);
		Shape17.mirror = true;
		setRotation(Shape17, 0F, 0F, 0F);

		/**
		 * Tomb Model
		 */
		Shape10 = new ModelRenderer(this, 24, 0);
		Shape10.addBox(-1F, -5F, -1F, 2, 3, 2);
		Shape10.setRotationPoint(7F, 26F, 7F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 0, 0);
		Shape11.addBox(-1F, -2F, -1F, 2, 3, 2);
		Shape11.setRotationPoint(-7F, 23F, 7F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 16, 0);
		Shape12.addBox(-1F, -2F, -1F, 2, 3, 2);
		Shape12.setRotationPoint(7F, 23F, -7F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 8, 0);
		Shape13.addBox(-1F, -2F, -1F, 2, 3, 2);
		Shape13.setRotationPoint(-7F, 23F, -7F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 17);
		Shape5.addBox(-7F, 0F, -7F, 14, 1, 14);
		Shape5.setRotationPoint(0F, 23F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 32, 0);
		Shape6.addBox(-4F, -2F, -4F, 8, 4, 8);
		Shape6.setRotationPoint(0F, 20F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 7, 21);
		Shape7.addBox(-5F, 0F, -5F, 10, 1, 10);
		Shape7.setRotationPoint(0F, 17F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 0, 5);
		Shape8.addBox(-4F, -5F, -2F, 8, 8, 4);
		Shape8.setRotationPoint(0F, 14F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 4, 19);
		Shape9.addBox(-6F, 0F, -6F, 12, 1, 12);
		Shape9.setRotationPoint(0F, 22F, 0F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);	

		/**
		 * Pillar model
		 */

		Base4 = new ModelRenderer(this, 0, 51);
		Base4.addBox(0F, 0F, 0F, 12, 1, 12);
		Base4.setRotationPoint(-6F, 23F, -6F);
		Base4.setTextureSize(64, 64);
		Base4.mirror = true;
		setRotation(Base4, 0F, 0F, 0F);
		Base5 = new ModelRenderer(this, 0, 52);
		Base5.addBox(0F, 0F, 0F, 11, 1, 11);
		Base5.setRotationPoint(-5.5F, 22.5F, -5.5F);
		Base5.setTextureSize(64, 64);
		Base5.mirror = true;
		setRotation(Base5, 0F, 0F, 0F);
		Base6 = new ModelRenderer(this, 0, 53);
		Base6.addBox(-5F, 0F, -5F, 10, 1, 10);
		Base6.setRotationPoint(0F, 22F, 0F);
		Base6.setTextureSize(64, 64);
		Base6.mirror = true;
		setRotation(Base6, 0F, 0F, 0F);
		Base7 = new ModelRenderer(this, 0, 54);
		Base7.addBox(-4.5F, 0F, -4.5F, 9, 1, 9);
		Base7.setRotationPoint(0F, 21.5F, 0F);
		Base7.setTextureSize(64, 64);
		Base7.mirror = true;
		setRotation(Base7, 0F, 0F, 0F);
		Base8 = new ModelRenderer(this, 0, 55);
		Base8.addBox(0F, 0F, 0F, 8, 1, 8);
		Base8.setRotationPoint(-4F, 21F, -4F);
		Base8.setTextureSize(64, 64);
		Base8.mirror = true;
		setRotation(Base8, 0F, 0F, 0F);
		Pillar1 = new ModelRenderer(this, 0, 20);
		Pillar1.addBox(0F, 0F, 0F, 7, 16, 2);
		Pillar1.setRotationPoint(-3.5F, 5F, -1F);
		Pillar1.setTextureSize(64, 64);
		Pillar1.mirror = true;
		setRotation(Pillar1, 0F, 0F, 0F);
		Base9 = new ModelRenderer(this, 0, 55);
		Base9.addBox(0F, 0F, 0F, 8, 1, 8);
		Base9.setRotationPoint(-4F, 4.5F, -4F);
		Base9.setTextureSize(64, 64);
		Base9.mirror = true;
		setRotation(Base9, 0F, 0F, 0F);
		Base10 = new ModelRenderer(this, 0, 54);
		Base10.addBox(0F, 0F, 0F, 9, 1, 9);
		Base10.setRotationPoint(-4.5F, 4F, -4.5F);
		Base10.setTextureSize(64, 64);
		Base10.mirror = true;
		setRotation(Base10, 0F, 0F, 0F);
		Pillar2 = new ModelRenderer(this, 18, 20);
		Pillar2.addBox(-1F, 5F, -3.5F, 2, 16, 7);
		Pillar2.setRotationPoint(0F, 0F, 0F);
		Pillar2.setTextureSize(64, 64);
		Pillar2.mirror = true;
		setRotation(Pillar2, 0F, 0F, 0F);
		Pillar3 = new ModelRenderer(this, 47, 20);
		Pillar3.addBox(-3F, 5F, -2F, 1, 16, 4);
		Pillar3.setRotationPoint(0F, 0F, 0F);
		Pillar3.setTextureSize(64, 64);
		Pillar3.mirror = true;
		setRotation(Pillar3, 0F, 0F, 0F);
		Pillar4 = new ModelRenderer(this, 47, 20);
		Pillar4.addBox(2F, 5F, -2F, 1, 16, 4);
		Pillar4.setRotationPoint(0F, 0F, 0F);
		Pillar4.setTextureSize(64, 64);
		Pillar4.mirror = true;
		setRotation(Pillar4, 0F, 0F, 0F);
		Pillar5 = new ModelRenderer(this, 37, 20);
		Pillar5.addBox(-2F, 5F, 2F, 4, 16, 1);
		Pillar5.setRotationPoint(0F, 0F, 0F);
		Pillar5.setTextureSize(64, 64);
		Pillar5.mirror = true;
		setRotation(Pillar5, 0F, 0F, 0F);
		Pillar6 = new ModelRenderer(this, 37, 20);
		Pillar6.addBox(-2F, 5F, -3F, 4, 16, 1);
		Pillar6.setRotationPoint(0F, 0F, 0F);
		Pillar6.setTextureSize(64, 64);
		Pillar6.mirror = true;
		setRotation(Pillar6, 0F, 0F, 0F);
		Shape18 = new ModelRenderer(this, 0, 0);
		Shape18.addBox(-2.5F, -4F, -2F, 5, 5, 5);
		Shape18.setRotationPoint(0F, -4F, 2F);
		Shape18.setTextureSize(64, 64);
		Shape18.mirror = true;
		setRotation(Shape18, -0.2268928F, 0F, 0F);
		Shape19 = new ModelRenderer(this, 0, 10);
		Shape19.addBox(-2.5F, -3F, -1F, 5, 7, 2);
		Shape19.setRotationPoint(0F, 0F, 0F);
		Shape19.setTextureSize(64, 64);
		Shape19.mirror = true;
		setRotation(Shape19, 0F, 0F, 0F);
		Shape20 = new ModelRenderer(this, 20, 0);
		Shape20.addBox(-1.5F, 0F, 0F, 1, 5, 1);
		Shape20.setRotationPoint(-2F, -2F, -1F);
		Shape20.setTextureSize(64, 64);
		Shape20.mirror = true;
		setRotation(Shape20, 0.5759587F, 0F, 0F);
		Shape21 = new ModelRenderer(this, 20, 0);
		Shape21.addBox(0.5F, 0F, 0F, 1, 5, 1);
		Shape21.setRotationPoint(2F, -2F, -1F);
		Shape21.setTextureSize(64, 64);
		Shape21.mirror = true;
		setRotation(Shape21, 0.5759587F, 0F, 0F);
		Shape22 = new ModelRenderer(this, 24, 0);
		Shape22.addBox(-3F, -0.3F, 0F, 4, 1, 1);
		Shape22.setRotationPoint(2.5F, 2F, 2F);
		Shape22.setTextureSize(64, 64);
		Shape22.mirror = true;
		setRotation(Shape22, 0F, 0F, 0.4363323F);
		Shape23 = new ModelRenderer(this, 24, 0);
		Shape23.addBox(-1F, -0.3F, 0F, 4, 1, 1);
		Shape23.setRotationPoint(-2.5F, 2F, 2F);
		Shape23.setTextureSize(64, 64);
		Shape23.mirror = true;
		setRotation(Shape23, 0F, 0F, -0.4363323F);
		/**
		 * Wooden cross Model
		 */
		Cross1 = new ModelRenderer(this, 0, 0);
		Cross1.addBox(-1F, -4F, -1F, 2, 17, 2);
		Cross1.setRotationPoint(0F, 11F, 0F);
		Cross1.setTextureSize(64, 64);
		Cross1.mirror = true;
		setRotation(Cross1, 0F, 0F, 0.0523599F);
		Cross2 = new ModelRenderer(this, 8, 0);
		Cross2.addBox(-6F, 0F, -0.5F, 12, 1, 1);
		Cross2.setRotationPoint(0F, 12F, 0F);
		Cross2.setTextureSize(64, 64);
		Cross2.mirror = true;
		setRotation(Cross2, 0F, 0F, -0.1047198F);
		Rope1 = new ModelRenderer(this, 8, 2);
		Rope1.addBox(-1.3F, 1F, 0.5F, 3, 1, 1);
		Rope1.setRotationPoint(-1F, 11F, 0F);
		Rope1.setTextureSize(64, 64);
		Rope1.mirror = true;
		setRotation(Rope1, 0F, 0F, -0.5235988F);
		Rope2 = new ModelRenderer(this, 8, 2);
		Rope2.addBox(0F, 0F, 0.3F, 3, 1, 1);
		Rope2.setRotationPoint(-1F, 11F, 0F);
		Rope2.setTextureSize(64, 64);
		Rope2.mirror = true;
		setRotation(Rope2, 0F, 0F, 0.5759587F);
		Rope3 = new ModelRenderer(this, 8, 2);
		Rope3.addBox(0F, 0F, -1.4F, 3, 1, 1);
		Rope3.setRotationPoint(-1F, 11F, 0F);
		Rope3.setTextureSize(64, 64);
		Rope3.mirror = true;
		setRotation(Rope3, 0F, 0F, 0.5759587F);
		Rope4 = new ModelRenderer(this, 8, 2);
		Rope4.addBox(-1.3F, 1F, -1.2F, 3, 1, 1);
		Rope4.setRotationPoint(-1F, 11F, 0F);
		Rope4.setTextureSize(64, 64);
		Rope4.mirror = true;
		setRotation(Rope4, 0F, 0F, -0.5235988F);
		ground1 = new ModelRenderer(this, 46, 8);
		ground1.addBox(-3F, -1F, -1F, 5, 2, 4);
		ground1.setRotationPoint(0F, 23F, -1F);
		ground1.setTextureSize(64, 64);
		ground1.mirror = true;
		setRotation(ground1, 0F, 0F, 0F);
		Ground2 = new ModelRenderer(this, 34, 0);
		Ground2.addBox(-5F, 0F, -4.5F, 8, 1, 7);
		Ground2.setRotationPoint(0F, 23F, 0F);
		Ground2.setTextureSize(64, 64);
		Ground2.mirror = true;
		setRotation(Ground2, 0F, 0F, 0F);
		ground3 = new ModelRenderer(this, 41, 0);
		ground3.addBox(0F, 0F, 0F, 1, 1, 1);
		ground3.setRotationPoint(4F, 23F, 0F);
		ground3.setTextureSize(64, 64);
		ground3.mirror = true;
		setRotation(ground3, 0F, 0F, 0F);
		ground4 = new ModelRenderer(this, 41, 0);
		ground4.addBox(0F, 0F, 0F, 1, 1, 1);
		ground4.setRotationPoint(0F, 23F, -6F);
		ground4.setTextureSize(64, 64);
		ground4.mirror = true;
		setRotation(ground4, 0F, 0F, 0F);
		ground5 = new ModelRenderer(this, 41, 0);
		ground5.addBox(0F, 0F, 0F, 1, 1, 1);
		ground5.setRotationPoint(-7F, 23F, 5F);
		ground5.setTextureSize(64, 64);
		ground5.mirror = true;
		setRotation(ground5, 0F, 0F, 0F);
		ground6 = new ModelRenderer(this, 41, 0);
		ground6.addBox(-6F, 0F, -5F, 1, 1, 1);
		ground6.setRotationPoint(0F, 23F, 0F);
		ground6.setTextureSize(64, 64);
		ground6.mirror = true;
		setRotation(ground6, 0F, 0F, 0F);
		ground7 = new ModelRenderer(this, 41, 0);
		ground7.addBox(3F, 0F, 4F, 1, 1, 1);
		ground7.setRotationPoint(0F, 23F, 0F);
		ground7.setTextureSize(64, 64);
		ground7.mirror = true;
		setRotation(ground7, 0F, 0F, 0F);
		ground8 = new ModelRenderer(this, 41, 0);
		ground8.addBox(-2F, 0.5F, 2F, 3, 1, 1);
		ground8.setRotationPoint(0F, 23F, 0F);
		ground8.setTextureSize(64, 64);
		ground8.mirror = true;
		setRotation(ground8, 0F, 0F, 0F);

		/**
		 * Angel Model
		 */
		b = new ModelRenderer(this, 0, 19);
		b.addBox(1F, -7F, 0F, 0, 13, 32);
		b.setRotationPoint(3F, 1F, 2F);
		b.setTextureSize(64, 64);
		b.mirror = true;
		setRotation(b, 0.1745329F, 0.7853982F, 0F);
		b1 = new ModelRenderer(this, 0, 19);
		b1.addBox(0F, -7F, 0F, 0, 13, 32);
		b1.setRotationPoint(-3F, 1F, 2F);
		b1.setTextureSize(64, 64);
		b1.mirror = true;
		setRotation(b1, 0.1745329F, -0.7853982F, 0F);
		b2 = new ModelRenderer(this, 40, 38);
		b2.addBox(1F, 0F, 0F, 1, 2, 11);
		b2.setRotationPoint(3F, 1F, 3F);
		b2.setTextureSize(64, 64);
		b2.mirror = true;
		setRotation(b2, 0.8901179F, 0.7853982F, 0F);
		b3 = new ModelRenderer(this, 38, 37);
		b3.addBox(0F, -1F, 0F, 1, 2, 12);
		b3.setRotationPoint(8.5F, -7F, 7F);
		b3.setTextureSize(64, 64);
		b3.mirror = true;
		setRotation(b3, 0.1570796F, 0.7853982F, 0F);
		b4 = new ModelRenderer(this, 40, 38);
		b4.addBox(-1F, 0F, 0F, 1, 2, 11);
		b4.setRotationPoint(-3F, 1F, 3F);
		b4.setTextureSize(64, 64);
		b4.mirror = true;
		setRotation(b4, 0.8901179F, -0.7853982F, 0F);
		b5 = new ModelRenderer(this, 38, 37);
		b5.addBox(0F, -1F, 0F, 1, 2, 12);
		b5.setRotationPoint(-8.5F, -7F, 7F);
		b5.setTextureSize(64, 64);
		b5.mirror = true;
		setRotation(b5, 0.1570796F, -0.7853982F, 0F);
		b8 = new ModelRenderer(this, 0, 41);
		b8.addBox(-5F, -1F, -4F, 11, 2, 8);
		b8.setRotationPoint(-0.5F, 23F, 0F);
		b8.setTextureSize(64, 64);
		b8.mirror = true;
		setRotation(b8, 0F, 0F, 0F);
		b9 = new ModelRenderer(this, 0, 42);
		b9.addBox(0F, -2F, 0F, 10, 3, 7);
		b9.setRotationPoint(-5F, 22F, -3.5F);
		b9.setTextureSize(64, 64);
		b9.mirror = true;
		setRotation(b9, 0F, 0F, 0F);
		b10 = new ModelRenderer(this, 0, 43);
		b10.addBox(-4.5F, -4F, -3F, 9, 2, 6);
		b10.setRotationPoint(0F, 22F, 0F);
		b10.setTextureSize(64, 64);
		b10.mirror = true;
		setRotation(b10, 0F, 0F, 0F);
		b11 = new ModelRenderer(this, 0, 40);
		b11.addBox(0F, 0F, 0F, 9, 1, 5);
		b11.setRotationPoint(-4.5F, 17F, -2.5F);
		b11.setTextureSize(64, 64);
		b11.mirror = true;
		setRotation(b11, 0F, 0F, 0F);
		b12 = new ModelRenderer(this, 0, 40);
		b12.addBox(-6.3F, 22F, 0F, 1, 2, 1);
		b12.setRotationPoint(0F, 0F, 0F);
		b12.setTextureSize(64, 64);
		b12.mirror = true;
		setRotation(b12, 0F, 0F, 0F);
		b13 = new ModelRenderer(this, 0, 41);
		b13.addBox(-5.7F, 0F, 0F, 1, 2, 1);
		b13.setRotationPoint(0F, 20F, 0F);
		b13.setTextureSize(64, 64);
		b13.mirror = true;
		setRotation(b13, 0F, 0F, 0F);
		b14 = new ModelRenderer(this, 0, 40);
		b14.addBox(-5.2F, 0F, 0F, 1, 3, 1);
		b14.setRotationPoint(0F, 17F, 0F);
		b14.setTextureSize(64, 64);
		b14.mirror = true;
		setRotation(b14, 0F, 0F, 0F);
		b15 = new ModelRenderer(this, 0, 40);
		b15.addBox(-4.7F, 0F, 0F, 1, 4, 1);
		b15.setRotationPoint(0F, 13F, 0F);
		b15.setTextureSize(64, 64);
		b15.mirror = true;
		setRotation(b15, 0F, 0F, 0F);
		b16 = new ModelRenderer(this, 0, 40);
		b16.addBox(-4.5F, 0F, 0F, 1, 2, 1);
		b16.setRotationPoint(0F, 11F, 0F);
		b16.setTextureSize(64, 64);
		b16.mirror = true;
		setRotation(b16, 0F, 0F, 0F);
		b17 = new ModelRenderer(this, 0, 40);
		b17.addBox(-3F, 0F, -4.5F, 2, 2, 1);
		b17.setRotationPoint(0F, 22F, 0F);
		b17.setTextureSize(64, 64);
		b17.mirror = true;
		setRotation(b17, 0F, 0F, 0F);
		b18 = new ModelRenderer(this, 0, 46);
		b18.addBox(-3F, -1F, -4F, 2, 2, 1);
		b18.setRotationPoint(0F, 21F, 0F);
		b18.setTextureSize(64, 64);
		b18.mirror = true;
		setRotation(b18, 0F, 0F, 0F);
		b19 = new ModelRenderer(this, 4, 45);
		b19.addBox(-2.5F, 0F, -3.5F, 1, 2, 1);
		b19.setRotationPoint(0F, 18F, 0F);
		b19.setTextureSize(64, 64);
		b19.mirror = true;
		setRotation(b19, 0F, 0F, 0F);
		b20 = new ModelRenderer(this, 11, 41);
		b20.addBox(-2.5F, 0F, -2.7F, 1, 4, 1);
		b20.setRotationPoint(0F, 14F, 0F);
		b20.setTextureSize(64, 64);
		b20.mirror = true;
		setRotation(b20, 0F, 0F, 0F);
		b21 = new ModelRenderer(this, 8, 45);
		b21.addBox(-2.5F, 12F, -2.3F, 1, 2, 1);
		b21.setRotationPoint(0F, 0F, 0F);
		b21.setTextureSize(64, 64);
		b21.mirror = true;
		setRotation(b21, 0F, 0F, 0F);
		b22 = new ModelRenderer(this, 4, 45);
		b22.addBox(5F, 0F, -4.5F, 1, 2, 1);
		b22.setRotationPoint(0F, 22F, 0F);
		b22.setTextureSize(64, 64);
		b22.mirror = true;
		setRotation(b22, 0F, 0F, 0F);
		b23 = new ModelRenderer(this, 0, 45);
		b23.addBox(4.5F, 20F, -4F, 1, 2, 1);
		b23.setRotationPoint(0F, 0F, 0F);
		b23.setTextureSize(64, 64);
		b23.mirror = true;
		setRotation(b23, 0F, 0F, 0F);
		b24 = new ModelRenderer(this, 0, 40);
		b24.addBox(4F, 0F, -3.5F, 1, 1, 1);
		b24.setRotationPoint(0F, 19F, 0F);
		b24.setTextureSize(64, 64);
		b24.mirror = true;
		setRotation(b24, 0F, 0F, 0F);
		b25 = new ModelRenderer(this, 0, 0);
		b25.addBox(-4F, -8F, -4F, 8, 8, 8);
		b25.setRotationPoint(0F, 0F, 0F);
		b25.setTextureSize(64, 64);
		b25.mirror = true;
		setRotation(b25, 0.2094395F, 0F, 0F);
		b252 = new ModelRenderer(this, 32, 0);
		b252.addBox(-4F, -8F, -4F, 8, 8, 8, 0.5f);
		b252.setRotationPoint(0F, 0F, 0F);
		b252.setTextureSize(64, 64);
		b252.mirror = true;
		setRotation(b252, 0.2094395F, 0F, 0F);
		b26 = new ModelRenderer(this, 16, 16);
		b26.addBox(-4F, 0F, -2F, 8, 12, 4);
		b26.setRotationPoint(0F, 0F, 0F);
		b26.setTextureSize(64, 64);
		b26.mirror = true;
		setRotation(b26, 0F, 0F, 0F);
		b27 = new ModelRenderer(this, 40, 16);
		b27.addBox(-3F, -2F, -2F, 4, 6, 4);
		b27.setRotationPoint(-5F, 2F, 0F);
		b27.setTextureSize(64, 64);
		b27.mirror = true;
		setRotation(b27, -0.3490659F, 0F, 0F);
		b28 = new ModelRenderer(this, 40, 16);
		b28.addBox(-1F, -2F, -2F, 4, 6, 4);
		b28.setRotationPoint(5F, 2F, 0F);
		b28.setTextureSize(64, 64);
		b28.mirror = true;
		setRotation(b28, -0.3490659F, 0F, 0F);
		b29 = new ModelRenderer(this, 0, 16);
		b29.addBox(-2F, 0F, -2F, 4, 12, 4);
		b29.setRotationPoint(-2F, 12F, 0F);
		b29.setTextureSize(64, 64);
		b29.mirror = true;
		setRotation(b29, 0F, 0F, 0F);
		b30 = new ModelRenderer(this, 0, 16);
		b30.addBox(-2F, 0F, -2F, 4, 12, 4);
		b30.setRotationPoint(2F, 12F, 0F);
		b30.setTextureSize(64, 64);
		b30.mirror = true;
		setRotation(b30, 0F, 0F, 0F);
		b7 = new ModelRenderer(this, 0, 32);
		b7.addBox(0F, -0.2F, 0.8F, 16, 4, 4);
		b7.setRotationPoint(-8F, 5F, -4F);
		b7.setTextureSize(64, 64);
		b7.mirror = true;
		setRotation(b7, -0.3490659F, 0F, 0F);
		
		/**
		 * Knight
		 */
		knight1 = new ModelRenderer(this, 0, 0);
		knight1.addBox(-4F, -8F, -4F, 8, 8, 8);
		knight1.setRotationPoint(0F, 0F, 0F);
		knight1.setTextureSize(64, 64);
		knight1.mirror = true;
		setRotation(knight1, 0F, 0F, 0F);
		knight2 = new ModelRenderer(this, 16, 16);
		knight2.addBox(-4F, 0F, -2F, 8, 12, 4);
		knight2.setRotationPoint(0F, 0F, 0F);
		knight2.setTextureSize(64, 64);
		knight2.mirror = true;
		setRotation(knight2, 0F, 0F, 0F);
		knight3 = new ModelRenderer(this, 40, 16);
		knight3.addBox(-3F, -2F, -2F, 4, 12, 4);
		knight3.setRotationPoint(-5F, 2F, 0F);
		knight3.setTextureSize(64, 64);
		knight3.mirror = true;
		setRotation(knight3, -0.8726646F, -0.3490659F, -0.3665191F);
		knight4 = new ModelRenderer(this, 40, 16);
		knight4.addBox(-1F, -2F, -2F, 4, 12, 4);
		knight4.setRotationPoint(5F, 2F, 0F);
		knight4.setTextureSize(64, 64);
		knight4.mirror = true;
		setRotation(knight4, -0.8726646F, 0.3490659F, 0.3665191F);
		knight5 = new ModelRenderer(this, 0, 16);
		knight5.addBox(-2F, 0F, -2F, 4, 12, 4);
		knight5.setRotationPoint(-2F, 12F, 0F);
		knight5.setTextureSize(64, 64);
		knight5.mirror = true;
		setRotation(knight5, 0F, 0F, 0F);
		knight6 = new ModelRenderer(this, 0, 16);
		knight6.addBox(-2F, 0F, -2F, 4, 12, 4);
		knight6.setRotationPoint(2F, 12F, 0F);
		knight6.setTextureSize(64, 64);
		knight6.mirror = true;
		setRotation(knight6, 0F, 0F, 0F);
		knight7 = new ModelRenderer(this, 0, 32);
		knight7.addBox(0F, 4F, -8F, 1, 4, 1);
		knight7.setRotationPoint(0F, 0F, 0F);
		knight7.setTextureSize(64, 64);
		knight7.mirror = true;
		setRotation(knight7, 0F, 0F, 0F);
		knight8 = new ModelRenderer(this, 12, 32);
		knight8.addBox(-2F, 8F, -8F, 5, 1, 1);
		knight8.setRotationPoint(0F, 0F, 0F);
		knight8.setTextureSize(64, 64);
		knight8.mirror = true;
		setRotation(knight8, 0F, 0F, 0F);
		knight9 = new ModelRenderer(this, 4, 32);
		knight9.addBox(-1F, 9F, -8F, 3, 13, 1);
		knight9.setRotationPoint(0F, 0F, 0F);
		knight9.setTextureSize(64, 64);
		knight9.mirror = true;
		setRotation(knight9, 0F, 0F, 0F);
		knight10 = new ModelRenderer(this, 12, 34);
		knight10.addBox(-0.5F, 22F, -8F, 2, 1, 1);
		knight10.setRotationPoint(0F, 0F, 0F);
		knight10.setTextureSize(64, 64);
		knight10.mirror = true;
		setRotation(knight10, 0F, 0F, 0F);
		knight11 = new ModelRenderer(this, 12, 36);
		knight11.addBox(0F, 23F, -8F, 1, 1, 1);
		knight11.setRotationPoint(0F, 0F, 0F);
		knight11.setTextureSize(64, 64);
		knight11.mirror = true;
		setRotation(knight11, 0F, 0F, 0F);
		knight12 = new ModelRenderer(this, 0, 46);
		knight12.addBox(-4.5F, -6F, -5F, 9, 3, 1);
		knight12.setRotationPoint(0F, 0F, 0F);
		knight12.setTextureSize(64, 64);
		knight12.mirror = true;
		setRotation(knight12, -0.122173F, 0F, 0F);
		knight13 = new ModelRenderer(this, 0, 46);
		knight13.addBox(-4.5F, -4F, -4F, 9, 3, 1);
		knight13.setRotationPoint(0F, 0F, 0F);
		knight13.setTextureSize(64, 64);
		knight13.mirror = true;
		setRotation(knight13, 0.122173F, 0F, 0F);
		knight14 = new ModelRenderer(this, 0, 50);
		knight14.addBox(-4.6F, -5F, -3F, 1, 3, 4);
		knight14.setRotationPoint(0F, 0F, 0F);
		knight14.setTextureSize(64, 64);
		knight14.mirror = true;
		setRotation(knight14, 0.3490659F, 0F, 0F);
		knight15 = new ModelRenderer(this, 0, 50);
		knight15.addBox(-4.7F, -7F, -3F, 1, 3, 3);
		knight15.setRotationPoint(0F, 0F, 0F);
		knight15.setTextureSize(64, 64);
		knight15.mirror = true;
		setRotation(knight15, 0.122173F, 0F, 0F);
		knight17 = new ModelRenderer(this, 0, 50);
		knight17.addBox(3.7F, -7F, -3F, 1, 3, 3);
		knight17.setRotationPoint(0F, 0F, 0F);
		knight17.setTextureSize(64, 64);
		knight17.mirror = true;
		setRotation(knight17, 0.122173F, 0F, 0F);
		knight16 = new ModelRenderer(this, 0, 50);
		knight16.addBox(3.6F, -5F, -3F, 1, 3, 4);
		knight16.setRotationPoint(0F, 0F, 0F);
		knight16.setTextureSize(64, 64);
		knight16.mirror = true;
		setRotation(knight16, 0.3490659F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
		Shape15.render(f5);
		Shape16.render(f5);
		Shape17.render(f5);
		Shape18.render(f5);
		Shape19.render(f5);
		Shape20.render(f5);
		Shape21.render(f5);
		Shape22.render(f5);
		Shape23.render(f5);
		Base1.render(f5);
		Base2.render(f5);
		Base3.render(f5);
		Base4.render(f5);
		Base5.render(f5);
		Base6.render(f5);
		Base7.render(f5);
		Base8.render(f5);
		Base9.render(f5);
		Base10.render(f5);
		Pillar1.render(f5);
		Pillar2.render(f5);
		Pillar3.render(f5);
		Pillar4.render(f5);
		Pillar5.render(f5);
		Pillar6.render(f5);
		Cross1.render(f5);
		Cross2.render(f5);
		Rope1.render(f5);
		Rope2.render(f5);
		Rope3.render(f5);
		Rope4.render(f5);
		ground1.render(f5);
		Ground2.render(f5);
		ground3.render(f5);
		ground4.render(f5);
		ground5.render(f5);
		ground6.render(f5);
		ground7.render(f5);
		ground8.render(f5);
		b.render(f5);
		b1.render(f5);
		b2.render(f5);
		b3.render(f5);
		b4.render(f5);
		b5.render(f5);
		b8.render(f5);
		b9.render(f5);
		b10.render(f5);
		b11.render(f5);
		b12.render(f5);
		b13.render(f5);
		b14.render(f5);
		b15.render(f5);
		b16.render(f5);
		b17.render(f5);
		b18.render(f5);
		b19.render(f5);
		b20.render(f5);
		b21.render(f5);
		b22.render(f5);
		b23.render(f5);
		b24.render(f5);
		b25.render(f5);
		b252.render(f5);
		b26.render(f5);
		b27.render(f5);
		b28.render(f5);
		b29.render(f5);
		b30.render(f5);
		b7.render(f5);
		knight1.render(f5);
		knight2.render(f5);
		knight3.render(f5);
		knight4.render(f5);
		knight5.render(f5);
		knight6.render(f5);
		knight7.render(f5);
		knight8.render(f5);
		knight9.render(f5);
		knight10.render(f5);
		knight11.render(f5);
		knight12.render(f5);
		knight13.render(f5);
		knight14.render(f5);
		knight15.render(f5);
		knight17.render(f5);

		

	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void showBasic(boolean b)
	{
		Shape1.showModel = Shape2.showModel =
				Shape3.showModel = Shape4.showModel =b;
	}
	public void showTomb(boolean b)
	{
		Shape5.showModel =Shape6.showModel =Shape7.showModel =Shape8.showModel =
				Shape9.showModel =Shape10.showModel =Shape11.showModel =
				Shape12.showModel =Shape13.showModel = b;
	}
	public void showZerk(boolean b)
	{
		Shape14.showModel =Shape15.showModel =Shape16.showModel =Shape17.showModel =
				Base1.showModel =Base2.showModel =Base3.showModel =b;
	}
	public void showPillar(boolean b)
	{
		Pillar1.showModel =Pillar2.showModel =Pillar3.showModel =Pillar4.showModel =Pillar5.showModel =Pillar6.showModel =
				Base4.showModel =Base5.showModel =Base6.showModel =Base7.showModel =Base8.showModel =Base9.showModel =
				Base10.showModel =b;
	}
	public void renderSkeleton(boolean b)
	{
		Shape18.showModel =Shape19.showModel =Shape20.showModel =Shape21.showModel =Shape22.showModel =
				Shape23.showModel = b;
	}
	public void renderCross(boolean b)
	{
		Cross1.showModel =Cross2.showModel =Rope1.showModel =Rope2.showModel =Rope3.showModel =Rope4.showModel =
				ground1.showModel =Ground2.showModel =ground3.showModel =ground4.showModel =ground5.showModel =
				ground6.showModel =ground7.showModel =ground8.showModel = b;
	}
	public void renderAngel(boolean bo)
	{
		b1.showModel =b.showModel =b2.showModel =b3.showModel =b4.showModel =b5.showModel=b252.showModel =b7.showModel =b8.showModel =
				b9.showModel =b10.showModel =b11.showModel =b12.showModel =b13.showModel =b14.showModel =b15.showModel =
				b16.showModel =b17.showModel =b18.showModel =b19.showModel =b20.showModel =b21.showModel =b22.showModel =
				b23.showModel =b24.showModel =b25.showModel =b26.showModel =b27.showModel =b28.showModel =b29.showModel=
				b30.showModel=bo;
	}
	public void renderKnight(boolean b)
	{
		knight1.showModel =knight2.showModel =knight3.showModel =knight4.showModel =knight5.showModel =knight6.showModel =
				knight7.showModel =knight8.showModel =knight9.showModel =knight10.showModel =knight11.showModel =knight12.showModel =
				knight13.showModel =knight14.showModel =knight15.showModel =knight16.showModel =knight17.showModel =b;
	}

	public void renderModel(float f5)
	{
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
		Shape15.render(f5);
		Shape16.render(f5);
		Shape17.render(f5);
		Shape18.render(f5);
		Shape19.render(f5);
		Shape20.render(f5);
		Shape21.render(f5);
		Shape22.render(f5);
		Shape23.render(f5);
		Base1.render(f5);
		Base2.render(f5);
		Base3.render(f5);
		Base4.render(f5);
		Base5.render(f5);
		Base6.render(f5);
		Base7.render(f5);
		Base8.render(f5);
		Base9.render(f5);
		Base10.render(f5);
		Pillar1.render(f5);
		Pillar2.render(f5);
		Pillar3.render(f5);
		Pillar4.render(f5);
		Pillar5.render(f5);
		Pillar6.render(f5);
		Cross1.render(f5);
		Cross2.render(f5);
		Rope1.render(f5);
		Rope2.render(f5);
		Rope3.render(f5);
		Rope4.render(f5);
		ground1.render(f5);
		Ground2.render(f5);
		ground3.render(f5);
		ground4.render(f5);
		ground5.render(f5);
		ground6.render(f5);
		ground7.render(f5);
		ground8.render(f5);
		b.render(f5);
		b1.render(f5);
		b2.render(f5);
		b3.render(f5);
		b4.render(f5);
		b5.render(f5);
		b8.render(f5);
		b9.render(f5);
		b10.render(f5);
		b11.render(f5);
		b12.render(f5);
		b13.render(f5);
		b14.render(f5);
		b15.render(f5);
		b16.render(f5);
		b17.render(f5);
		b18.render(f5);
		b19.render(f5);
		b20.render(f5);
		b21.render(f5);
		b22.render(f5);
		b23.render(f5);
		b24.render(f5);
		b25.render(f5);
		b252.render(f5);
		b26.render(f5);
		b27.render(f5);
		b28.render(f5);
		b29.render(f5);
		b30.render(f5);
		b7.render(f5);
		knight1.render(f5);
		knight2.render(f5);
		knight3.render(f5);
		knight4.render(f5);
		knight5.render(f5);
		knight6.render(f5);
		knight7.render(f5);
		knight8.render(f5);
		knight9.render(f5);
		knight10.render(f5);
		knight11.render(f5);
		knight12.render(f5);
		knight13.render(f5);
		knight14.render(f5);
		knight15.render(f5);
		knight17.render(f5);
	}
}