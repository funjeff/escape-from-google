package json;

import java.util.ArrayList;

public class JSONArray {

	private ArrayList<Object> values;
	
	public JSONArray () {
		values = new ArrayList<Object> ();
	}
	
	public JSONArray (String value) throws JSONException {
		//TODO Optimize this maybe
		//Call the default constructor
		this ();
		
		//Remove whitespace
		StringBuilder noWhitespaceBuilder = new StringBuilder ();
		boolean remove = true;
		for (int i = 0; i < value.length(); i ++) {
			if (value.charAt(i) == '"') {
				remove = !remove;
			}
			if (!remove || !Character.isWhitespace(value.charAt(i))) {
				noWhitespaceBuilder.append (value.charAt(i));
			}
		}
		String noWhitespace = noWhitespaceBuilder.toString ();
		if (noWhitespace.charAt (0) == '[' && noWhitespace.charAt (noWhitespace.length () - 1) == ']') {
			noWhitespace = noWhitespace.substring (1, noWhitespace.length () - 1);
		} else {
			throw new JSONException ("Input String is not a valid JSON Array");
		}
		//Main parsing loop
		int i = 0;
		while (i < noWhitespace.length ()) {
			StringBuilder working;
			
			//Parse out value
			if (noWhitespace.charAt (i) == '{') {
				//Value is a JSONObject
				working = new StringBuilder ();
				working.append ("{");
				i++;
				int bracketCount = 1;
				while (true) {
					if (noWhitespace.charAt (i) == '{') {
						bracketCount++;
					} else if (noWhitespace.charAt (i) == '}') {
						if (bracketCount == 1) {
							//End of bracket
							working.append ('}');
							break;
						} else {
							bracketCount--;
						}
					}
					working.append (noWhitespace.charAt (i));
					i++;
					if (i >= noWhitespace.length ()) {
						throw new JSONException ("Missing } when parsing token " + value);
					}
				}
				values.add (new JSONObject (working.toString ()));
			} else if (noWhitespace.charAt (i) == '[') {
				//Value is a JSONArray
				working = new StringBuilder ();
				working.append ("[");
				i++;
				int bracketCount = 1;
				while (true) {
					if (noWhitespace.charAt (i) == '[') {
						bracketCount++;
					} else if (noWhitespace.charAt (i) == ']') {
						if (bracketCount == 1) {
							//End of bracket
							working.append ("]");
							break;
						} else {
							bracketCount--;
						}
					}
					working.append (noWhitespace.charAt (i));
					i++;
					if (i >= noWhitespace.length ()) {
						throw new JSONException ("Missing ] when parsing token " + value);
					}
				}
				values.add (new JSONArray (working.toString ()));
			} else {
				//Value is a JSON literal
				working = new StringBuilder ();
				while (i < noWhitespace.length () && noWhitespace.charAt (i) != ',') {
					working.append (noWhitespace.charAt (i++));
				}
				values.add (JSONUtil.getValueOfJSONLiteral (working.toString ()));
			}
			i++;
			if (i < noWhitespace.length () && noWhitespace.charAt (i) == ',') {
				i++;
			}
		}
	}
	
	/**
	 * Returns the Object at the given index
	 * @param index the index to use
	 * @return the Object at the given index
	 */
	public Object get (int index) {
		return values.get (index);
	}
	
	/**
	 * Sets the object at the given index to the given Object
	 * @param obj the Object to use
	 * @param index the index to use
	 */
	public void set (Object obj, int index) {
		values.set (index, obj);
	}
	
	/**
	 * Removes the object at the given index
	 * @param index the index to remove
	 */
	public void remove (int index) {
		values.remove (index);
	}
	
	/**
	 * Adds the given Object to this JSONArray
	 * @param obj the Object to add
	 */
	public void add (Object obj) {
		values.add (obj);
	}
	
	/**
	 * Removes the given Object from this JSONArray
	 * @param obj the Object to remove
	 */
	public void remove (Object obj) {
		values.remove (obj);
	}
	
	/**
	 * Gets an array list representing the contents of this JSONArray
	 * @return this JSONArray as an ArrayList
	 */
	public ArrayList<Object> getContents () {
		return values;
	}
	
	@Override
	public String toString () {
		String working = "[";
		for (int i = 0; i < values.size (); i ++) {
			Object o = values.get (i);
			if (o instanceof String) {
				working += '\"' + (String)o + '\"';
			} else {
				working += o.toString ();
			}
			if (i != values.size () - 1) {
				working += ",";
			}
		}
		working += "]";
		return working;
	}
}
