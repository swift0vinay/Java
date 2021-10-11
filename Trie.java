class TrieNode {
	int end;
	TrieNode list[];

	TrieNode() {
		this.end = 0;
		// depends upon total node for alphabet 26 digits 9
		this.list = new TrieNode[26];
	}

	boolean containsKey(char x) {
		return this.list[x - 'a'] != null;
	}

	void setNode(char x, TrieNode node) {
		this.list[x - 'a'] = node;
	}

	TrieNode getNode(char x) {
		return this.list[x - 'a'];
	}
}

class Trie {
	TrieNode root = new TrieNode();

	void insert(String word) {
		TrieNode curr = root;
		for (int i = 0; i < word.length(); i++) {
			char x = word.charAt(i);
			if (!curr.containsKey(x)) {
				curr.setNode(x, new TrieNode());
			}
			curr = curr.getNode(x);
		}
		curr.end++;
	}

	boolean searchWord(String word) {
		TrieNode curr = root;
		for (int i = 0; i < word.length(); i++) {
			char x = word.charAt(i);
			if (!curr.containsKey(x)) {
				return false;
			}
			curr = curr.getNode(x);
		}
		return curr.end > 0 ? true : false;
	}

	boolean isPrefix(String word) {
		TrieNode curr = root;
		for (int i = 0; i < word.length(); i++) {
			char x = word.charAt(i);
			if (!curr.containsKey(x)) {
				return false;
			}
			curr = curr.getNode(x);
		}
		return true;
	}
}
