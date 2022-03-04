package com.wbm.plugin.util;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class BlockTool {
	public static void fillBlockWithMaterial(Location pos1, Location pos2, Material mat) {
		World world = pos1.getWorld();
		int pos1X = (int) pos1.getX();
		int pos2X = (int) pos2.getX();
		int pos1Y = (int) pos1.getY();
		int pos2Y = (int) pos2.getY();
		int pos1Z = (int) pos1.getZ();
		int pos2Z = (int) pos2.getZ();

		// get difference
		int dx = MathTool.getDiff(pos1X, pos2X);
		int dy = MathTool.getDiff(pos1Y, pos2Y);
		int dz = MathTool.getDiff(pos1Z, pos2Z);

		// get smaller x, y, z
		int smallX = Math.min(pos1X, pos2X);
		int smallY = Math.min(pos1Y, pos2Y);
		int smallZ = Math.min(pos1Z, pos2Z);

		/*
		 * for문에서 <=dx인 이유: 만약 (1,1) ~ (3,3) 면적의 블럭을 지정하면 총 9개의 블럭을 가리키는것인데 위에서 dx, dy,
		 * dz를 구할때 차이를 구하므로 3-1 = 2 즉 2칸만을 의미하게 되서 <=을 해줘서 3칸을 채우게 함
		 */
		for (int y = 0; y <= dy; y++) {
			for (int z = 0; z <= dz; z++) {
				for (int x = 0; x <= dx; x++) {
					Location loc = new Location(world, smallX, smallY, smallZ);
					loc.add(x, y, z);

					// set type
					loc.getBlock().setType(mat);
				}
			}
		}
	}

	public static void fillBlockWithRandomMaterial(Location pos1, Location pos2, List<Material> mats) {
		World world = pos1.getWorld();
		int pos1X = (int) pos1.getX();
		int pos2X = (int) pos2.getX();
		int pos1Y = (int) pos1.getY();
		int pos2Y = (int) pos2.getY();
		int pos1Z = (int) pos1.getZ();
		int pos2Z = (int) pos2.getZ();

		// get difference
		int dx = MathTool.getDiff(pos1X, pos2X);
		int dy = MathTool.getDiff(pos1Y, pos2Y);
		int dz = MathTool.getDiff(pos1Z, pos2Z);

		// get smaller x, y, z
		int smallX = Math.min(pos1X, pos2X);
		int smallY = Math.min(pos1Y, pos2Y);
		int smallZ = Math.min(pos1Z, pos2Z);

		/*
		 * for문에서 <=dx인 이유: 만약 (1,1) ~ (3,3) 면적의 블럭을 지정하면 총 9개의 블럭을 가리키는것인데 위에서 dx, dy,
		 * dz를 구할때 차이를 구하므로 3-1 = 2 즉 2칸만을 의미하게 되서 <=을 해줘서 3칸을 채우게 함
		 */
		for (int y = 0; y <= dy; y++) {
			for (int z = 0; z <= dz; z++) {
				for (int x = 0; x <= dx; x++) {
					Location loc = new Location(world, smallX, smallY, smallZ);
					loc.add(x, y, z);

					// set type
					Material randomMat = mats.get((int) (Math.random() * mats.size()));
					loc.getBlock().setType(randomMat);
				}
			}
		}
	}

	public static void fillBlockWithItemStack(Location pos1, Location pos2, List<ItemStack> items) {
		World world = pos1.getWorld();
		int pos1X = (int) pos1.getX();
		int pos2X = (int) pos2.getX();
		int pos1Y = (int) pos1.getY();
		int pos2Y = (int) pos2.getY();
		int pos1Z = (int) pos1.getZ();
		int pos2Z = (int) pos2.getZ();

		// get difference
		int dx = MathTool.getDiff(pos1X, pos2X);
		int dy = MathTool.getDiff(pos1Y, pos2Y);
		int dz = MathTool.getDiff(pos1Z, pos2Z);

		// get smaller x, y, z
		int smallX = Math.min(pos1X, pos2X);
		int smallY = Math.min(pos1Y, pos2Y);
		int smallZ = Math.min(pos1Z, pos2Z);

		int index = 0;
		/*
		 * for문에서 <=dx인 이유: 만약 (1,1) ~ (3,3) 면적의 블럭을 지정하면 총 9개의 블럭을 가리키는것인데 위에서 dx, dy,
		 * dz를 구할때 차이를 구하므로 3-1 = 2 즉 2칸만을 의미하게 되서 <=을 해줘서 3칸을 채우게 함
		 */
		for (int y = 0; y <= dy; y++) {
			for (int z = 0; z <= dz; z++) {
				for (int x = 0; x <= dx; x++) {
					Location loc = new Location(world, smallX, smallY, smallZ);
					loc.add(x, y, z);

					ItemStack item = items.get(index);
					Material mat = item.getType();
					// set type
					loc.getBlock().setType(mat);

					index++;
				}
			}
		}
	}

	public static boolean isAllSameBlock(Location pos1, Location pos2) {
		// 전체와 비교할 기준 ItemStack 가져오기
		ItemStack STDItemStack = ItemStackTool.item(pos1.getBlock().getType());
		return isAllSameBlockWithItemStack(pos1, pos2, STDItemStack);
	}

	public static boolean isAllSameBlockWithItemStack(Location pos1, Location pos2, ItemStack targetItem) {
		World world = pos1.getWorld();
		// 전체와 비교할 ItemStack 가져오기
		int pos1X = (int) pos1.getX();
		int pos2X = (int) pos2.getX();
		int pos1Y = (int) pos1.getY();
		int pos2Y = (int) pos2.getY();
		int pos1Z = (int) pos1.getZ();
		int pos2Z = (int) pos2.getZ();

		// get difference
		int dx = MathTool.getDiff(pos1X, pos2X);
		int dy = MathTool.getDiff(pos1Y, pos2Y);
		int dz = MathTool.getDiff(pos1Z, pos2Z);

		// get smaller x, y, z
		int smallX = Math.min(pos1X, pos2X);
		int smallY = Math.min(pos1Y, pos2Y);
		int smallZ = Math.min(pos1Z, pos2Z);

		/*
		 * for문에서 <=dx인 이유: 만약 (1,1) ~ (3,3) 면적의 블럭을 지정하면 총 9개의 블럭을 가리키는것인데 위에서 dx, dy,
		 * dz를 구할때 차이를 구하므로 3-1 = 2 즉 2칸만을 의미하게 되서 <=을 해줘서 3칸을 채우게 함
		 */
		for (int y = 0; y <= dy; y++) {
			for (int z = 0; z <= dz; z++) {
				for (int x = 0; x <= dx; x++) {
					Location loc = new Location(world, smallX, smallY, smallZ);
					loc.add(x, y, z);

					// 비교
					Block locB = loc.getBlock();
					ItemStack locItemStack = ItemStackTool.item(locB.getType());

					if (!(targetItem.equals(locItemStack))) {
						// 하나라도 같지 않으면 false
						return false;
					}
				}
			}
		}

		// 모두 같으면 true
		return true;
	}

	public static boolean containsBlock(Location pos1, Location pos2, Material block) {
		World world = pos1.getWorld();
		// 전체와 비교할 ItemStack 가져오기
		int pos1X = (int) pos1.getX();
		int pos2X = (int) pos2.getX();
		int pos1Y = (int) pos1.getY();
		int pos2Y = (int) pos2.getY();
		int pos1Z = (int) pos1.getZ();
		int pos2Z = (int) pos2.getZ();

		// get difference
		int dx = MathTool.getDiff(pos1X, pos2X);
		int dy = MathTool.getDiff(pos1Y, pos2Y);
		int dz = MathTool.getDiff(pos1Z, pos2Z);

		// get smaller x, y, z
		int smallX = Math.min(pos1X, pos2X);
		int smallY = Math.min(pos1Y, pos2Y);
		int smallZ = Math.min(pos1Z, pos2Z);

		/*
		 * for문에서 <=dx인 이유: 만약 (1,1) ~ (3,3) 면적의 블럭을 지정하면 총 9개의 블럭을 가리키는것인데 위에서 dx, dy,
		 * dz를 구할때 차이를 구하므로 3-1 = 2 즉 2칸만을 의미하게 되서 <=을 해줘서 3칸을 채우게 함
		 */
		for (int y = 0; y <= dy; y++) {
			for (int z = 0; z <= dz; z++) {
				for (int x = 0; x <= dx; x++) {
					Location loc = new Location(world, smallX, smallY, smallZ);
					loc.add(x, y, z);

					Block locB = loc.getBlock();
					if (locB.getType() == block) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static void replaceBlocks(Location pos1, Location pos2, Material oldBlock, Material newBlock) {
		World world = pos1.getWorld();
		int pos1X = (int) pos1.getX();
		int pos2X = (int) pos2.getX();
		int pos1Y = (int) pos1.getY();
		int pos2Y = (int) pos2.getY();
		int pos1Z = (int) pos1.getZ();
		int pos2Z = (int) pos2.getZ();

		// get difference
		int dx = MathTool.getDiff(pos1X, pos2X);
		int dy = MathTool.getDiff(pos1Y, pos2Y);
		int dz = MathTool.getDiff(pos1Z, pos2Z);

		// get smaller x, y, z
		int smallX = Math.min(pos1X, pos2X);
		int smallY = Math.min(pos1Y, pos2Y);
		int smallZ = Math.min(pos1Z, pos2Z);

		/*
		 * for문에서 <=dx인 이유: 만약 (1,1) ~ (3,3) 면적의 블럭을 지정하면 총 9개의 블럭을 가리키는것인데 위에서 dx, dy,
		 * dz를 구할때 차이를 구하므로 3-1 = 2 즉 2칸만을 의미하게 되서 <=을 해줘서 3칸을 채우게 함
		 */
		for (int y = 0; y <= dy; y++) {
			for (int z = 0; z <= dz; z++) {
				for (int x = 0; x <= dx; x++) {
					Location loc = new Location(world, smallX, smallY, smallZ);
					loc.add(x, y, z);

					// set type
					Material blockMat = loc.getBlock().getType();
					if (blockMat == oldBlock) {
						loc.getBlock().setType(newBlock);
					}
				}
			}
		}
	}

	public static void remainBlocks(Location pos1, Location pos2, List<Material> blocks) {
		World world = pos1.getWorld();
		int pos1X = (int) pos1.getX();
		int pos2X = (int) pos2.getX();
		int pos1Y = (int) pos1.getY();
		int pos2Y = (int) pos2.getY();
		int pos1Z = (int) pos1.getZ();
		int pos2Z = (int) pos2.getZ();

		// get difference
		int dx = MathTool.getDiff(pos1X, pos2X);
		int dy = MathTool.getDiff(pos1Y, pos2Y);
		int dz = MathTool.getDiff(pos1Z, pos2Z);

		// get smaller x, y, z
		int smallX = Math.min(pos1X, pos2X);
		int smallY = Math.min(pos1Y, pos2Y);
		int smallZ = Math.min(pos1Z, pos2Z);

		/*
		 * for문에서 <=dx인 이유: 만약 (1,1) ~ (3,3) 면적의 블럭을 지정하면 총 9개의 블럭을 가리키는것인데 위에서 dx, dy,
		 * dz를 구할때 차이를 구하므로 3-1 = 2 즉 2칸만을 의미하게 되서 <=을 해줘서 3칸을 채우게 함
		 */
		for (int y = 0; y <= dy; y++) {
			for (int z = 0; z <= dz; z++) {
				for (int x = 0; x <= dx; x++) {
					Location loc = new Location(world, smallX, smallY, smallZ);
					loc.add(x, y, z);

					// set type
					Material blockMat = loc.getBlock().getType();
					if (!blocks.contains(blockMat)) {
						loc.getBlock().setType(Material.AIR);
					}
				}
			}
		}
	}
}
