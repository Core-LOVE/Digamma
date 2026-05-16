import shutil

fire_type = input("Type Fire's Name: ")

shutil.copy2('copper_fire_0.png.mcmeta', fire_type + '_fire_0.png.mcmeta')
shutil.copy2('copper_fire_1.png.mcmeta', fire_type + '_fire_1.png.mcmeta')