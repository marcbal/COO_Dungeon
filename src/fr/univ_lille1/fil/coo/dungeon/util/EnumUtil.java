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
	
	/**
	 * Permet de rechercher l'existance d'un élément dans un enum, de façon insensible à la casse
	 * @param enumType la classe correpondant à l'enum à lister
	 * @param search l'élément à rechercher, insensible à la casse
	 * @return l'élément de l'énumarétion, si elle a été trouvée, null sinon
	 */
	public static <T extends Enum<T>> T searchEnum(Class<T> enumType, String search) {
		T[] elements = enumType.getEnumConstants();
		
	    for (T el : elements)
	        if (el.name().equalsIgnoreCase(search))
	            return el;
	    return null;
	}

}
