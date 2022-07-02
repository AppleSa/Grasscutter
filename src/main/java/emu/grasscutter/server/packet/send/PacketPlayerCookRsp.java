package emu.grasscutter.server.packet.send;

import emu.grasscutter.game.inventory.GameItem;
import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.CookRecipeDataOuterClass.CookRecipeData;
import emu.grasscutter.net.proto.ItemParamOuterClass.ItemParam;
import emu.grasscutter.net.proto.PlayerCookRspOuterClass.PlayerCookRsp;
import emu.grasscutter.net.proto.RetcodeOuterClass.Retcode;

public class PacketPlayerCookRsp extends BasePacket {
    public PacketPlayerCookRsp(Retcode retcode) {
        super(PacketOpcodes.PlayerCookRsp);

        PlayerCookRsp proto = PlayerCookRsp.newBuilder()
            .setRetcode(retcode.getNumber())
            .build();

        this.setData(proto);
    }

    public PacketPlayerCookRsp(GameItem output, int quality, int count, int recipeId, int proficiency) {
        super(PacketOpcodes.PlayerCookRsp);

        PlayerCookRsp proto = PlayerCookRsp.newBuilder()
            .setRecipeData(
                CookRecipeData.newBuilder()
                    .setRecipeId(recipeId)
                    .setProficiency(proficiency)    
            )
            .setQteQuality(quality)
            .addItemList(
                ItemParam.newBuilder()
                    .setItemId(output.getItemId())
                    .setCount(output.getCount())
            )
            .setCookCount(count)
            .build();

        this.setData(proto);
    }
}
