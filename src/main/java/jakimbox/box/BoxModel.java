package jakimbox.box;

import jakimbox.prefab.model.BasicModel;
import net.minecraft.client.model.ModelRenderer;

public class BoxModel extends BasicModel
{
    ModelRenderer RootNode;
    ModelRenderer mainbox;
    ModelRenderer Spring1;
    ModelRenderer boxlid;
    ModelRenderer crank1;
    ModelRenderer crank2;
    ModelRenderer crank3;
    ModelRenderer crank4;
    ModelRenderer Spring2;
    ModelRenderer Spring3;
    ModelRenderer Spring4;
    ModelRenderer Head;
    ModelRenderer Hat1;
    ModelRenderer Hat2;
    ModelRenderer Hat2a;
    ModelRenderer Hat2b;
    ModelRenderer Hat2c;
    ModelRenderer Hat3;
    ModelRenderer Hat4;
    ModelRenderer Hat5;
    ModelRenderer Hat6;
    ModelRenderer Hat7;

    public BoxModel()
    {
        this(0.0f);
    }

    public BoxModel(float par1)
    {
        RootNode = new ModelRenderer(this, 0, 0);
        RootNode.setTextureSize(128, 128);
        RootNode.addBox(0F, 0F, 0F, 0, 0, 0);
        RootNode.setRotationPoint(0F, 24F, 0F);
        mainbox = new ModelRenderer(this, 0, 0);
        mainbox.setTextureSize(128, 128);
        mainbox.addBox(-3.6F, -3.6F, -3.6F, 12, 12, 12);
        mainbox.setRotationPoint(2.4F, 19.2F, -2.4F);
        Spring1 = new ModelRenderer(this, 0, 56);
        Spring1.setTextureSize(128, 128);
        Spring1.addBox(-1.5F, -0.9F, -1.5F, 5, 3, 5);
        Spring1.setRotationPoint(0.9999999F, 18F, -1F);
        boxlid = new ModelRenderer(this, 0, 24);
        boxlid.setTextureSize(128, 128);
        boxlid.addBox(-3.6F, -1.8F, -3.6F, 12, 6, 12);
        boxlid.setRotationPoint(2.4F, 19.2F, 2.4F);
        crank1 = new ModelRenderer(this, 50, 0);
        crank1.setTextureSize(128, 128);
        crank1.addBox(-0.3F, -0.3F, -0.9F, 1, 1, 3);
        crank1.setRotationPoint(-2.8F, 21.2F, 1.66893E-07F);
        crank2 = new ModelRenderer(this, 50, 5);
        crank2.setTextureSize(128, 128);
        crank2.addBox(-0.3F, -0.3F, -0.3F, 1, 1, 1);
        crank2.setRotationPoint(-3.6F, 21.2F, 2.145767E-07F);
        crank3 = new ModelRenderer(this, 50, 8);
        crank3.setTextureSize(128, 128);
        crank3.addBox(-0.3F, -0.3F, -1.2F, 1, 1, 4);
        crank3.setRotationPoint(-3.6F, 20.4F, 2.145767E-07F);
        crank4 = new ModelRenderer(this, 50, 14);
        crank4.setTextureSize(128, 128);
        crank4.addBox(-0.6F, -0.6F, -0.3F, 2, 2, 1);
        crank4.setRotationPoint(-2.4F, 21F, -0.1999999F);
        Spring2 = new ModelRenderer(this, 0, 56);
        Spring2.setTextureSize(128, 128);
        Spring2.addBox(-2.5F, -1.5F, -2.5F, 5, 3, 5);
        Spring2.setRotationPoint(0F, 17.5F, 0F);
        Spring3 = new ModelRenderer(this, 0, 56);
        Spring3.setTextureSize(128, 128);
        Spring3.addBox(-2.5F, -1.5F, -2.5F, 5, 3, 5);
        Spring3.setRotationPoint(0.3132978F, 16.33386F, -0.007613325F);
        Spring4 = new ModelRenderer(this, 0, 56);
        Spring4.setTextureSize(128, 128);
        Spring4.addBox(-2.5F, -1.5F, -2.5F, 5, 3, 5);
        Spring4.setRotationPoint(1.030457F, 15.25192F, -0.01752002F);
        Head = new ModelRenderer(this, 61, 0);
        Head.setTextureSize(128, 128);
        Head.addBox(-8F, -8F, -8F, 16, 16, 16);
        Head.setRotationPoint(1.5F, 13.2F, 0F);
        Hat1 = new ModelRenderer(this, 2, 73);
        Hat1.setTextureSize(128, 128);
        Hat1.addBox(-4.5F, -0.5F, -4.5F, 9, 1, 9);
        Hat1.setRotationPoint(1.564372F, 12.90728F, -0.0131466F);
        Hat2 = new ModelRenderer(this, 10, 73);
        Hat2.setTextureSize(128, 128);
        Hat2.addBox(-0.5F, -0.5F, -4.5F, 1, 1, 9);
        Hat2.setRotationPoint(-0.7618161F, 12.85818F, 1.029176F);
        Hat2a = new ModelRenderer(this, 10, 73);
        Hat2a.setTextureSize(128, 128);
        Hat2a.addBox(-0.5F, -0.5F, -4.5F, 1, 1, 9);
        Hat2a.setRotationPoint(3.629869F, 13.92014F, -1.112163F);
        Hat2b = new ModelRenderer(this, 8, 81);
        Hat2b.setTextureSize(128, 128);
        Hat2b.addBox(-5.5F, -0.5F, -0.5F, 11, 1, 1);
        Hat2b.setRotationPoint(0.4322655F, 12.98556F, -2.296172F);
        Hat2c = new ModelRenderer(this, 8, 81);
        Hat2c.setTextureSize(128, 128);
        Hat2c.addBox(-5.5F, -0.5F, -0.5F, 11, 1, 1);
        Hat2c.setRotationPoint(2.435788F, 13.79277F, 2.213185F);
        Hat3 = new ModelRenderer(this, 10, 75);
        Hat3.setTextureSize(128, 128);
        Hat3.addBox(-2.5F, -1.5F, -2.5F, 5, 3, 5);
        Hat3.setRotationPoint(1.929149F, 11.24855F, -0.08764397F);
        Hat4 = new ModelRenderer(this, 12, 77);
        Hat4.setTextureSize(128, 128);
        Hat4.addBox(-2F, -1F, -2F, 4, 2, 4);
        Hat4.setRotationPoint(2.186245F, 10.47865F, 0.05781616F);
        Hat5 = new ModelRenderer(this, 14, 78);
        Hat5.setTextureSize(128, 128);
        Hat5.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
        Hat5.setRotationPoint(2.464601F, 9.811668F, 0.2979173F);
        Hat6 = new ModelRenderer(this, 16, 78);
        Hat6.setTextureSize(128, 128);
        Hat6.addBox(-1F, -1.5F, -1F, 2, 3, 2);
        Hat6.setRotationPoint(2.876305F, 9.02028F, 0.7498071F);
        Hat7 = new ModelRenderer(this, 18, 80);
        Hat7.setTextureSize(128, 128);
        Hat7.addBox(-0.5F, -1F, -0.5F, 1, 2, 1);
        Hat7.setRotationPoint(3.334576F, 8.391197F, 1.377694F);
    }

    @Override
    public void render(float scale)
    {
        RootNode.rotateAngleX = 0F;
        RootNode.rotateAngleY = -1.570796F;
        RootNode.rotateAngleZ = 0F;
        RootNode.renderWithRotation(scale);

        mainbox.rotateAngleX = 0F;
        mainbox.rotateAngleY = -1.570796F;
        mainbox.rotateAngleZ = 0F;
        mainbox.renderWithRotation(scale);

        Spring1.rotateAngleX = 0F;
        Spring1.rotateAngleY = -1.570796F;
        Spring1.rotateAngleZ = 0F;
        Spring1.renderWithRotation(scale);

        boxlid.rotateAngleX = 0F;
        boxlid.rotateAngleY = -1.570796F;
        boxlid.rotateAngleZ = -0.9250245F;
        boxlid.renderWithRotation(scale);

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

        crank4.rotateAngleX = 0F;
        crank4.rotateAngleY = -1.570796F;
        crank4.rotateAngleZ = 0F;
        crank4.renderWithRotation(scale);

        Spring2.rotateAngleX = 0.1745304F;
        Spring2.rotateAngleY = -1.22173F;
        Spring2.rotateAngleZ = 2.800432E-09F;
        Spring2.renderWithRotation(scale);

        Spring3.rotateAngleX = 0.2407574F;
        Spring3.rotateAngleY = -1.047198F;
        Spring3.rotateAngleZ = -1.205532E-08F;
        Spring3.renderWithRotation(scale);

        Spring4.rotateAngleX = 0.3348594F;
        Spring4.rotateAngleY = -0.8726645F;
        Spring4.rotateAngleZ = -3.254348E-08F;
        Spring4.renderWithRotation(scale);

        Head.rotateAngleX = -0.05343039F;
        Head.rotateAngleY = 0.4420456F;
        Head.rotateAngleZ = 0.2143333F;
        Head.renderWithRotation(scale);

        Hat1.rotateAngleX = -0.1621518F;
        Hat1.rotateAngleY = 0.4181065F;
        Hat1.rotateAngleZ = 0.2169126F;
        Hat1.renderWithRotation(scale);

        Hat2.rotateAngleX = -0.1621518F;
        Hat2.rotateAngleY = 0.4181066F;
        Hat2.rotateAngleZ = 0.2169126F;
        Hat2.renderWithRotation(scale);

        Hat2a.rotateAngleX = -0.1621518F;
        Hat2a.rotateAngleY = 0.4181066F;
        Hat2a.rotateAngleZ = 0.2169126F;
        Hat2a.renderWithRotation(scale);

        Hat2b.rotateAngleX = -0.1621518F;
        Hat2b.rotateAngleY = 0.4181066F;
        Hat2b.rotateAngleZ = 0.2169126F;
        Hat2b.renderWithRotation(scale);

        Hat2c.rotateAngleX = -0.1621518F;
        Hat2c.rotateAngleY = 0.4181066F;
        Hat2c.rotateAngleZ = 0.2169126F;
        Hat2c.renderWithRotation(scale);

        Hat3.rotateAngleX = -0.1621518F;
        Hat3.rotateAngleY = 0.4181065F;
        Hat3.rotateAngleZ = 0.2169126F;
        Hat3.renderWithRotation(scale);

        Hat4.rotateAngleX = -0.4547835F;
        Hat4.rotateAngleY = 0.347191F;
        Hat4.rotateAngleZ = 0.2386833F;
        Hat4.renderWithRotation(scale);

        Hat5.rotateAngleX = -0.610443F;
        Hat5.rotateAngleY = 0.3010236F;
        Hat5.rotateAngleZ = 0.2622008F;
        Hat5.renderWithRotation(scale);

        Hat6.rotateAngleX = -0.84468F;
        Hat6.rotateAngleY = 0.2063779F;
        Hat6.rotateAngleZ = 0.3256054F;
        Hat6.renderWithRotation(scale);

        Hat7.rotateAngleX = -1.007175F;
        Hat7.rotateAngleY = 0.1025953F;
        Hat7.rotateAngleZ = 0.4088473F;
        Hat7.renderWithRotation(scale);

    }

}
