package tk.cth451.transitrailmod.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.blocks.prototype.PlatformBlock;
import tk.cth451.transitrailmod.init.ModItems;

public class PlatformPanelBlock extends PlatformBlock {
	
	public PlatformPanelBlock(Material materialIn) {
		super(Material.IRON);
		this.setUnlocalizedName("platform_panel_block");
		this.setDefaultState(getDefaultState().withProperty(UPPER, false).withProperty(FACING, EnumFacing.NORTH));
	}
	
	// Properties
	public boolean isUpper(IBlockAccess worldIn, BlockPos pos){
		return worldIn.getBlockState(pos.down()).getBlock().equals(this);
	}
	
	public boolean isUpper(IBlockState state){
		return (Boolean) state.getValue(UPPER);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = (EnumFacing) state.getValue(FACING);
		if (isUpper(source, pos)) {
			if (facing == EnumFacing.NORTH) {
				return new AxisAlignedBB(0.0F, 0.0F, 0.125F, 1.0F, 0.5F, 0.25F);
			} else if (facing == EnumFacing.EAST) {
				return new AxisAlignedBB(0.75F, 0.0F, 0.0F, 0.875F, 0.5F, 1.0F);
			} else if (facing == EnumFacing.SOUTH) {
				return new AxisAlignedBB(0.0F, 0.0F, 0.75F, 1.0F, 0.5F, 0.875F);
			} else { // if (facing == EnumFacing.WEST) {
				return new AxisAlignedBB(0.125F, 0.0F, 0.0F, 0.25F, 0.5F, 1.0F);
			}
		} else {
			if (facing == EnumFacing.NORTH) {
				return new AxisAlignedBB(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.25F);
			} else if (facing == EnumFacing.EAST) {
				return new AxisAlignedBB(0.75F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
			} else if (facing == EnumFacing.SOUTH) {
				return new AxisAlignedBB(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 0.875F);
			} else { // if (facing == EnumFacing.WEST) {
				return new AxisAlignedBB(0.125F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
			}
		}
	}
	
	// Blockstates
	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING, UPPER});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}
	
	// Redstone and interactions
	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos)
	{
		return this.getItem();
	}
	
	private Item getItem() {
		return ModItems.platform_panel_item;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return this.getItem();
	}
}
