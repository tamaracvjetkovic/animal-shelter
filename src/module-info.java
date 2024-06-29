/**
 * 
 */
/**
 * @author darinkaloncar
 *
 */
module AnimalShelter {
    requires xstream;
    requires java.desktop;

    exports domain.serializeddata to xstream;
    opens domain.serializeddata to xstream;
    opens domain.model to xstream;
}