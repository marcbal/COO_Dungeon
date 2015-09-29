package fr.univ_lille1.fil.coo.dungeon.util;

public class EnumUtil {

	/**
	 * List all enum constants which are in the specified enum class.
	 * @param enumType the enum class.
	 * @param separator a string which will be used as a separator
	 * @return a string representation of the enum class.
	 */
	public static <T extends Enum<T>> String enumList(Class<T> enumType, String separator) {
		T[] elements = enumType.getEnumConstants();
		
		String out = "";
		boolean first = true;
		for (T el : elements) {
			if (!first) {
				out += separator;
			}
			first = false;
			out += el.name();
			
		}
		return out;
	}
	

	/**
	 * List all enum constants which are in the specified enum class. It is equivalent to call
	 * {@link #enumList(Class, String)} with the second parameter <code>", "</code>
	 * @param enumType the enum class.
	 * @return a string representation of the enum class.
	 */
	public static <T extends Enum<T>> String enumList(Class<T> enumType) {
		return enumList(enumType, ", ");
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
