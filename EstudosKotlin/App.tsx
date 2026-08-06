import React, { useEffect, useState } from 'react';
import { StyleSheet, Text, View, NativeModules, StatusBar, useColorScheme } from 'react-native';
import { SafeAreaProvider, useSafeAreaInsets } from 'react-native-safe-area-context';

// Puxa o seu módulo Kotlin criado no getName()
const { HelloModule } = NativeModules;

function App() {
  const isDarkMode = useColorScheme() === 'dark';

  return (
    <SafeAreaProvider>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <AppContent />
    </SafeAreaProvider>
  );
}

function AppContent() {
  const safeAreaInsets = useSafeAreaInsets();
  const [mensagem, setMensagem] = useState('Carregando dados do Kotlin...');

  useEffect(() => {
    const nativeHelloModule = HelloModule as typeof HelloModule & {
      getHello?: (payload?: unknown) => Promise<string>;
    };

    if (nativeHelloModule?.getHello) {
      nativeHelloModule
        .getHello()
        .then((resultado: string) => setMensagem(resultado))
        .catch((erro: any) => {
          console.error('Erro ao chamar o Kotlin:', erro);
          setMensagem('Erro ao chamar o Kotlin.');
        });
    } else {
      setMensagem("Módulo 'HelloModule' não foi encontrado no nativo.");
    }
  }, []);

  return (
    <View 
      style={[
        styles.container, 
        { 
          paddingTop: safeAreaInsets.top, 
          paddingBottom: safeAreaInsets.bottom 
        }
      ]}
    >
      <View style={styles.card}>
        <Text style={styles.titulo}>Compilação React Native + Kotlin</Text>
        <Text style={styles.texto}>{mensagem}</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f0f2f5',
  },
  card: {
    backgroundColor: '#ffffff',
    padding: 24,
    borderRadius: 12,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
    elevation: 3,
    alignItems: 'center',
    width: '80%',
  },
  titulo: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333333',
    marginBottom: 12,
  },
  texto: {
    fontSize: 16,
    color: '#007aff',
    textAlign: 'center',
    fontWeight: '600',
  },
});

export default App;