package fr.univ_lille1.fil.coo.dungeon.util;

public class EnumUtil {
	
	/**
	 * Permet de lister les constantes d'un enum passé en paramètre, dans une chaine de caractère
	 * @param enumType la classe correspondant à l'enum à lister
	 * @return une chaine de caractère représentant les éléments de l'enum
	 */
	public static <T extends Enum<T>> String enumList(Class<T> enumType) {
		T[] elements = enumType.getEnumConstants();
		
		String out = "";
		boolean first = true;
		for (T el : elements) {
			if (!first) {
				out += ", ";
				first = false;
			}
			out += el.name();
			
		}
		return out;
	}

}
