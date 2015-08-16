package jakimbox.box;

import jakimbox.prefab.model.BasicModel;
import net.minecraft.client.model.ModelRenderer;

public class BoxModel extends BasicModel
{
    ModelRenderer mainbox;
    ModelRenderer boxlid;
    ModelRenderer crank4;
    ModelRenderer crank1;
    ModelRenderer crank2;
    ModelRenderer crank3;
    ModelRenderer Spring1;
    ModelRenderer Spring2;
    ModelRenderer Spring3;
    ModelRenderer Spring4;
    ModelRenderer Head;
    ModelRenderer Hat1;
    ModelRenderer Hat3;
    ModelRenderer Hat4;
    ModelRenderer Hat5;
    ModelRenderer Hat6;
    ModelRenderer Hat7;
    ModelRenderer Hat2a;
    ModelRenderer Hat2c;
    ModelRenderer Hat2b;
    ModelRenderer Hat2;

    public BoxModel()
    {
        mainbox = new ModelRenderer(this, 0, 0);
        mainbox.setTextureSize(128, 128);
        mainbox.addBox(-6F, -6F, -6F, 12, 12, 12);
        mainbox.setRotationPoint(0F, 18F, 0F);
        boxlid = new ModelRenderer(this, 0, 24);
        boxlid.setTextureSize(128, 128);
        boxlid.addBox(0F, 0F, -6F, 12, 6, 12);
        boxlid.setRotationPoint(3.576278E-07F, 12F, 5.999999F);
        crank4 = new ModelRenderer(this, 50, 14);
        crank4.setTextureSize(128, 128);
        crank4.addBox(-1F, -1F, -0.5F, 2, 2, 1);
        crank4.setRotationPoint(-6F, 17.5F, -0.5F);
        crank1 = new ModelRenderer(this, 50, 0);
        crank1.setTextureSize(128, 128);
        crank1.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
        crank1.setRotationPoint(-8F, 17.5F, -0.4999999F);
        crank2 = new ModelRenderer(this, 50, 5);
        crank2.setTextureSize(128, 128);
        crank2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        crank2.setRotationPoint(-8.999999F, 16.5F, -0.4999999F);
        crank3 = new ModelRenderer(this, 50, 8);
        crank3.setTextureSize(128, 128);
        crank3.addBox(-0.5F, -0.5F, -2F, 1, 1, 4);
        crank3.setRotationPoint(-10.5F, 15.5F, -0.4999999F);
        Spring1 = new ModelRenderer(this, 0, 56);
        Spring1.setTextureSize(128, 128);
        Spring1.addBox(-1.5F, -0.9F, -1.5F, 5, 3, 5);
        Spring1.setRotationPoint(0.9999999F, 10F, -0.9999999F);
        Spring2 = new ModelRenderer(this, 0, 56);
        Spring2.setTextureSize(128, 128);
        Spring2.addBox(-2.5F, -1.5F, -2.5F, 5, 3, 5);
        Spring2.setRotationPoint(1F, 8F, -4.263256E-14F);
        Spring3 = new ModelRenderer(this, 0, 56);
        Spring3.setTextureSize(128, 128);
        Spring3.addBox(-2.5F, -1.5F, -2.5F, 5, 3, 5);
        Spring3.setRotationPoint(2F, 5F, -0.007613156F);
        Spring4 = new ModelRenderer(this, 0, 56);
        Spring4.setTextureSize(128, 128);
        Spring4.addBox(-2.5F, -1.5F, -2.5F, 5, 3, 5);
        Spring4.setRotationPoint(3.5F, 2F, -0.02000044F);
        Head = new ModelRenderer(this, 65, 64);
        Head.setTextureSize(128, 128);
        Head.addBox(-4F, -4F, -4F, 8, 8, 8);
        Head.setRotationPoint(4F, -2F, -7.152557E-07F);
        Hat1 = new ModelRenderer(this, 61, 39);
        Hat1.setTextureSize(128, 128);
        Hat1.addBox(-5.5F, -0.5F, -5.5F, 11, 1, 11);
        Hat1.setRotationPoint(4.260691F, -2.963757F, 0.05669332F);
        Hat3 = new ModelRenderer(this, 10, 75);
        Hat3.setTextureSize(128, 128);
        Hat3.addBox(-2.5F, -1.5F, -2.5F, 5, 3, 5);
        Hat3.setRotationPoint(5.46092F, -7.400913F, 0.3177135F);
        Hat4 = new ModelRenderer(this, 12, 77);
        Hat4.setTextureSize(128, 128);
        Hat4.addBox(-2F, -1F, -2F, 4, 2, 4);
        Hat4.setRotationPoint(6.035326F, -9.195881F, 0.6055781F);
        Hat5 = new ModelRenderer(this, 14, 78);
        Hat5.setTextureSize(128, 128);
        Hat5.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
        Hat5.setRotationPoint(6.546574F, -10.59308F, 0.9611805F);
        Hat6 = new ModelRenderer(this, 16, 78);
        Hat6.setTextureSize(128, 128);
        Hat6.addBox(-1F, -1.5F, -1F, 2, 3, 2);
        Hat6.setRotationPoint(7.007617F, -11.47612F, 1.46881F);
        Hat7 = new ModelRenderer(this, 18, 80);
        Hat7.setTextureSize(128, 128);
        Hat7.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        Hat7.setRotationPoint(7.999712F, -12.67959F, 2.906659F);
        Hat2a = new ModelRenderer(this, 69, 39);
        Hat2a.setTextureSize(128, 128);
        Hat2a.addBox(-0.5F, -0.5F, -6.5F, 1, 1, 13);
        Hat2a.setRotationPoint(9.270022F, -0.725647F, -2.569611F);
        Hat2c = new ModelRenderer(this, 74, 35);
        Hat2c.setTextureSize(128, 128);
        Hat2c.addBox(-5.5F, -0.5F, -0.5F, 11, 1, 1);
        Hat2c.setRotationPoint(6.425004F, -1.026325F, 5.4011F);
        Hat2b = new ModelRenderer(this, 69, 54);
        Hat2b.setTextureSize(128, 128);
        Hat2b.addBox(-5.5F, -0.5F, -0.5F, 11, 1, 1);
        Hat2b.setRotationPoint(1.616547F, -2.963633F, -5.42136F);
        Hat2 = new ModelRenderer(this, 76, 37);
        Hat2.setTextureSize(128, 128);
        Hat2.addBox(-0.5F, -0.5F, -6.5F, 1, 1, 13);
        Hat2.setRotationPoint(-1.270022F, -3.274357F, 2.569609F);
    }

    @Override
    public void render(float scale)
    {
        scale = 0.028F;
        mainbox.rotateAngleX = 0F;
        mainbox.rotateAngleY = -1.570796F;
        mainbox.rotateAngleZ = 0F;
        mainbox.renderWithRotation(scale);

        boxlid.rotateAngleX = -3.761167E-08F;
        boxlid.rotateAngleY = -1.570796F;
        boxlid.rotateAngleZ = -0.9250247F;
        boxlid.renderWithRotation(scale);

        crank4.rotateAngleX = 0F;
        crank4.rotateAngleY = -1.570796F;
        crank4.rotateAngleZ = 0F;
        crank4.renderWithRotation(scale);

        crank1.rotateAngleX = 0F;
        crank1.rotateAngleY = -1.570796F;
        crank1.rotateAngleZ = 0F;
        crank1.renderWithRotation(scale);

        crank2.rotateAngleX = 1.570796F;
        crank2.rotateAngleY = -1.570796F;
        crank2.rotateAngleZ = 0F;
        crank2.renderWithRotation(scale);

        crank3.rotateAngleX = 0F;
        crank3.rotateAngleY = -1.570796F;
        crank3.rotateAngleZ = 0F;
        crank3.renderWithRotation(scale);

        Spring1.rotateAngleX = 0F;
        Spring1.rotateAngleY = -1.570796F;
        Spring1.rotateAngleZ = 0F;
        Spring1.renderWithRotation(scale);

        Spring2.rotateAngleX = 0.1738746F;
        Spring2.rotateAngleY = -1.288551F;
        Spring2.rotateAngleZ = -0.01575596F;
        Spring2.renderWithRotation(scale);

        Spring3.rotateAngleX = 0.2406002F;
        Spring3.rotateAngleY = -1.010339F;
        Spring3.rotateAngleZ = 0.008786623F;
        Spring3.renderWithRotation(scale);

        Spring4.rotateAngleX = 0.3348595F;
        Spring4.rotateAngleY = -0.8726642F;
        Spring4.rotateAngleZ = 7.430474E-08F;
        Spring4.renderWithRotation(scale);

        Head.rotateAngleX = -0.1621518F;
        Head.rotateAngleY = 0.4181067F;
        Head.rotateAngleZ = 0.2169127F;
        Head.renderWithRotation(scale);

        Hat1.rotateAngleX = -0.1621518F;
        Hat1.rotateAngleY = 0.4181066F;
        Hat1.rotateAngleZ = 0.2169127F;
        Hat1.renderWithRotation(scale);

        Hat3.rotateAngleX = -0.1621518F;
        Hat3.rotateAngleY = 0.4181067F;
        Hat3.rotateAngleZ = 0.2169126F;
        Hat3.renderWithRotation(scale);

        Hat4.rotateAngleX = -0.4547837F;
        Hat4.rotateAngleY = 0.3471914F;
        Hat4.rotateAngleZ = 0.2386833F;
        Hat4.renderWithRotation(scale);

        Hat5.rotateAngleX = -0.6104434F;
        Hat5.rotateAngleY = 0.301024F;
        Hat5.rotateAngleZ = 0.2622008F;
        Hat5.renderWithRotation(scale);

        Hat6.rotateAngleX = -0.8446801F;
        Hat6.rotateAngleY = 0.2063781F;
        Hat6.rotateAngleZ = 0.3256054F;
        Hat6.renderWithRotation(scale);

        Hat7.rotateAngleX = -1.007175F;
        Hat7.rotateAngleY = 0.1025955F;
        Hat7.rotateAngleZ = 0.4088475F;
        Hat7.renderWithRotation(scale);

        Hat2a.rotateAngleX = -0.1621518F;
        Hat2a.rotateAngleY = 0.4181069F;
        Hat2a.rotateAngleZ = 0.2169126F;
        Hat2a.renderWithRotation(scale);

        Hat2c.rotateAngleX = -0.1621518F;
        Hat2c.rotateAngleY = 0.4181069F;
        Hat2c.rotateAngleZ = 0.2169126F;
        Hat2c.renderWithRotation(scale);

        Hat2b.rotateAngleX = -0.1621518F;
        Hat2b.rotateAngleY = 0.4181069F;
        Hat2b.rotateAngleZ = 0.2169126F;
        Hat2b.renderWithRotation(scale);

        Hat2.rotateAngleX = -0.1621518F;
        Hat2.rotateAngleY = 0.4181067F;
        Hat2.rotateAngleZ = 0.2169126F;
        Hat2.renderWithRotation(scale);

    }
}
